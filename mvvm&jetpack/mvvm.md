
MVVM(Jetpack中的三剑客Lifecycle+ViewModel+LiveData) 


一：Lifecycle  可以让任何一个类 与Activity的生命周期向绑定


Lifecycle 的目标只有三个：
(1) 实现生命周期 管理的一致性，做到 “一处修改、处处生效”。
(2)让第三方组件能够 随时在自己内部拿到生命周期状态，以便执行 及时叫停 错过时机的 异步业务 等操作。
(3)让第三方组件在调试时 能够 更方便和安全地追踪到 事故所在的生命周期源。


 Lifecycle 具体是依赖什么机制运作的呢？
  正是基于观察者模式
将需要被 生命周期调用 和 状态通知 的第三方组件，作为观察者添加到 List / Map 中，并在 生命周期持有者（LifecycleOwner） Activity / Fragment 等发生生命周期节点变化时，通过事件分发代理者（LifecycleRegister）以遍历的方式通知所有观察者执行相应的逻辑。

在 Activity / Fragment（LifecycleOwner）里,发生生命周期节点变化时,

通过事件分发代理者LifecycleRegister/Lifecycle,通知所有观察者observer
lifecycleOwner.lifecycle.addObserver(observer)









案例demo 
1:在Activity(ComponentActivity)/Fragment里,自己实现了 LifecycleOwner接口，直接实现getLifecycle()方法
 所以自己就是被观察者,自己一旦发生生命周期的变化,就会立马通知发送给观察者Observer
 执行lifecycle.addObserver(LifecycleObserverImpl(lifecycle))方法给 观察者Observer发送消息数据通知
         
        class MainActivity : AppCompatActivity() {
		   override fun onCreate(savedInstanceState: Bundle?) {
		        super.onCreate(savedInstanceState)
		        setContentView(R.layout.activity_main)
		        this.lifecycle.addObserver(MockMediaPlayer2(this))
		 
		    }
		
		  }


	
	    	public interface LifecycleOwner {
			     @NonNull
			    Lifecycle getLifecycle();
			}
 
      
			public abstract class Lifecycle {
			 @MainThread
			    public abstract void addObserver(@NonNull LifecycleObserver observer);
			 
	        public enum Event {
	         ON_CREATE,
	         ON_START,
	         ON_RESUME,
	         ON_PAUSE,
	         ON_STOP,
	         ON_DESTROY,
	         ON_ANY
	        }

		        public enum State {
		        DESTROYED,
		        INITIALIZED,
		        CREATED,
		        STARTED,
		        RESUMED;
		        public boolean isAtLeast(@NonNull State state) {
		            return compareTo(state) >= 0;
		        }
		    }
		     }


2.创建观察者Observer,观察者必须实现LifecycleEventObserver(LifecycleObserver)接口,
 实现onStateChanged( LifecycleOwner source, Lifecycle.Event event)方法,
  参数1:LifecycleOwner 就是Activity/Fragment
  参数2:event  就是Activity/Fragment的生命周期状态, 将这些状态发送给观察者,观察者接收 Activity/Fragment发送过来的生命周期状态
               做相应的业务逻辑处理。
         
        public interface LifecycleEventObserver extends LifecycleObserver {
         void onStateChanged( LifecycleOwner source,  Lifecycle.Event event);
         }

        public interface LifecycleObserver {}



       class MyLifecycleObserver : LifecycleEventObserver {
       
           override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("MockMediaPlayer3", "onCreate")
                player = MediaPlayer()
            }
            Lifecycle.Event.ON_START, Lifecycle.Event.ON_RESUME -> {
                if (owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    player.start()
                    Log.d("MockMediaPlayer3", "onResume")
                }
            }
            Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP -> {
                player.stop()
                Log.d("MockMediaPlayer3", "onPause")
            }
            Lifecycle.Event.ON_DESTROY -> {
                player.release()
                Log.d("MockMediaPlayer3", "onDestroy")
            }
            Lifecycle.Event.ON_ANY -> Log.d("MockMediaPlayer3", "onAny")
        }
    }

 }

  
              





  
 
![](https://images.xiaozhuanlan.com/photo/2020/e73eea883e8a46ef47a09fbb2629def0.png)




 





**二:ViewModel 中间层，对数据的传递起着“承上启下”的职责(ViewModel用于代替MVP中的Presenter)**
https://xiaozhuanlan.com/topic/6257931840
     ViewModel 果真是某些人所说的，是 MVP Presenter 的阉割版。答案是否定的。


  1.ViewModel 的目标只有三个:
  (1)让状态管理独立于视图控制器，从而做到重建状态的分治、状态在多页面的共享，以及跨页面通信。 
  (2)为状态设置作用域，使数据状态的共享做到作用域可控。
   **（2.1）对数据源状态共享的控制的原理解释:  **
			首先，ViewModel 的职责是专门管理状态，尤其是那些 “重新获取的代价比较大” 的状态（例如通过网络请求到的 List 等数据，抛开 “费流量” 不说，https 请求本身涉及大量的加解密运算，耗费 CPU 资源，耗电显著）。   
            并且，ViewModel 归根结底是被对应的 Activity/Fragment 持有。只不过 ViewModel 和 Lifecycle 一样，通过模版方法模式封装得太好了，乃至于开发者感觉不到 它实际上与视图控制器的关系十分紧密，并不是表面看上去的 “那么独立” 和 “逍遥法外”。
             

           通过阅读源码我们知道,每个视图控制器(Activity(ComponentActivity) 和 Fragment) 继承ComponentActivity
           ComponentActivity  实现接口 interface ViewModelStoreOwner  
           每个 ViewModelStoreOwner 持有着唯一的一个 ViewModelStore，也即 每个视图控制器(Activity(ComponentActivity) 和 Fragment)都持有自己的一个 ViewModelStore。    
            ViewModelStore 的作用是维护一个 Map，来管理视图控制器所持有的所有 ViewModel。    
             所以三者的关系是，ViewModelStoreOwner 持有 ViewModelStore 持有 ViewModel维护了一个HashMap<String, ViewModel>集合。
             ViewModelStoreOwner - ViewModelStore - HashMap - ViewModel
             作为 ViewModelStoreOwner 的视图控制器(Activity(ComponentActivity) 和 Fragment)被 destory 时（重建的情况除外），ViewModelStore 会被通知去完成清除状态操作，从而将 Map 中管理着的 ViewModel 全部走一遍 clear 方法，并且清空 Map。
            （clear 方法是 final 级，不可修改，clear 方法中包含 onClear 钩子，开发者可重写 onClear 方法来自定义数据的清空）

  
             注意:在这里我们要把ComponentActivity当成自己的Activity 有自己的生命周期实现逻辑
                    在ComponentActivity里添加生命周期的状态变化观察,当ComponentActivity生命周期状态为ON_DESTROY时,就会通知
                    观察者LifecycleEventObserver接收事件,当接收的事件Event为ON_DESTROY时处理逻辑是:调用getViewModelStore().clear()
                      清除ViewModelStore的mMap集合里的所有的ViewModel对象




         public class ComponentActivity extends androidx.core.app.ComponentActivity implements  LifecycleOwner,
         ViewModelStoreOwner{
             
                  public ComponentActivity() {
                  Lifecycle lifecycle = getLifecycle();
                   getLifecycle().addObserver(new LifecycleEventObserver() {
			            @Override
			            public void onStateChanged(@NonNull LifecycleOwner source,
			                    @NonNull Lifecycle.Event event) {
			                if (event == Lifecycle.Event.ON_DESTROY) {
			                    if (!isChangingConfigurations()) {
			                        getViewModelStore().clear();
			                    }
			                }
			            }
			        });
              }
             }




            public interface ViewModelStoreOwner {
              ViewModelStore getViewModelStore();
              }
         
             public class ViewModelStore {

			    private final HashMap<String, ViewModel> mMap = new HashMap<>();
			
			    final void put(String key, ViewModel viewModel) {
			        ViewModel oldViewModel = mMap.put(key, viewModel);
			        if (oldViewModel != null) {
			            oldViewModel.onCleared();
			        }
			    }
			
			    final ViewModel get(String key) {
			        return mMap.get(key);
			    }
			
			    Set<String> keys() {
			        return new HashSet<>(mMap.keySet());
			    }
			
			     public final void clear() {
			        for (ViewModel vm : mMap.values()) {
			            vm.clear();
			        }
			        mMap.clear();
			    }
			}

              
   **（2.2）如何做到对数据源状态共享的控制的？**  
         通过 “工厂模式” 来搞定：
         在 Fragment 页面中创建 ViewModelProvider 实例，并在入参中注入 ViewModelStoreOwner，
 


   (3)实现单向依赖，避免内存泄漏。







让 页面 和 业务 完全分离，使得 业务 能够完全独立于特定页面的生命周期，单独地存活和工作。并且业务能够为多个页面共享，无需每个页面都单独创建一个业务实例。
有些状态是跨页面共享的，也即退出某个页面时，状态不会跟随该页面的 destory 而被清空。
另有些状态是私有的，那么当退出该页面时，状态跟随页面的 destory 而被清空。







  1.分担Acivity的工作,减少Acivity的数据业务逻辑, 存放与界面相关的所有数据

  2.ViewModel的生命周期与Acitivy 生命周期不同, 屏幕旋转，Acitivy会重新创建,之前是数据会丢失,将界面的数据存储在ViewModel,屏幕旋转，ViewModel不会重新创建,数据不会丢失
  , 只有当Acitivy推出销毁,其对应的ViewModel才会销毁



 3. 如何如何创建ViewModel的子类对象 MainViewModel        给MainViewModel层传递数据
    
			 3.1 创建MainViewModel  对象  
			 lateinit var viewModel: MainViewModel    
			  viewModel = ViewModelProviders.of(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
			
			 
			
			
			3.2  通过  ViewModelProvider.Factory  给MainViewModel 构造方法  传递参数数据
			
			
			class MainViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
			
			    override fun <T : ViewModel> create(modelClass: Class<T>): T {
			        return MainViewModel(countReserved) as T
			    }
			
			}
			
			
			
			
			3.3MainViewModel 类 继承  ViewModel()
			
			
			 class MainViewModel(countReserved: Int) : ViewModel(){
			}




 

**三: LiveData<T> (子类 MutableLiveData<T> )   **
     LiveData被观察者对象,通过postValue() setValue()存储数据源,在各个Activity/fragment 通过
      liveData.observe(lifecycleOwner,Observer(观察者)) 向观察者发送数据源数据,Observer(观察者)接收数据进行数据的展示
 
      LiveData<T> 的泛型T可以表示为包装的任何数据对象的数据类型,
      被包装的数据对象T 一旦发生数据变化, 就会通知给Activity/fragment 进行数据的更行
      
       



  1. 使用LiveData背景:
  在 LiveData 面市前，我们分发状态，数据回调,多是通过 EventBus 或 Java Interface 来完成的。不管你是用于网络请求回调的情况，还是跨页面通信的情况。
那这造成了什么问题呢？首先，EventBus 只是纯粹的 Bus，它 缺乏上述提到的 标准化开发理念 的约束，那么人们在使用这个框架时，容易因 去中心化 地滥用，而造成 诸如 毫无防备地收到 预期外的 不明来源的数据推送、拿到过时的数据 及 事件源追溯复杂度 为 n² 的局面。
如果使用 EventBus，那么我们须在每个监听状态的页面、在特定的生命周期节点中 手动地注册和解绑 EventBus，这使得 因疏忽导致的编码不一致 而带来不可预期错误 的概率大大提升（例如在页面步入 onDestroy 后，收到消息并调用了可能已为 null 的视图实例 … ）
并且，在缺乏理念约束的情况下，随意使用 EventBus，将难以追踪 “真正的事件源” —— 事件源只有 1、2 个还好，如果是像今天这个 29 个页面的复杂项目，在调试时，光是找事件源，恐怕就得让人欲仙欲死。EventBus 追踪事件源的复杂度是 n * (n - 1)，简言之就是 n²。
再者，在缺乏理念约束的情况下，通过 EventBus 从页面发送通知，难以总是确保消息的正确性 和 可靠性 —— 也许当前页面的信息是过时的呢？那么所有收到通知的页面，拿到的数据也就过时了，这非常影响用户体验。


2.关于LiveData的observe(  LifecycleOwner owner,   Observer<? super T> observer) 方法使用
** 参数owner**:
          传入的 LifeCycleOwner 从 fragment.this 改进为 Fragment.getViewLifeCycleOwner(在 onCreateView 之后和 onDestroyView 之前使用)
          如此设计 主要是为了解决 getView() 的生命长度 比 fragment 短（仅存活于 onCreateView 之后和 onDestroyView 之前），导致某些时候 fragment 其他成员还活着，但 getView() 为 null 的 生命周期安全问题，
            Activity 则不用改变。
 **参数observer**:观察者接收唯一可靠数据源,进行数据的显示

 
 3.LiveData 的目标只有三个：
  (1)在 Lifecycle 的帮助下，实现生命周期管理的一致性，以及订阅者回调的生命周期安全。
 (2)遵循 “唯一可信源” 理念的约束，提供 “读写分离” 的支持，以便规避 “跨域消息同步” 等场景下 高频 存在的数据一致性问题、使团队新手也能 不假思索写出低风险代码。
 (3)就算不用 DataBinding，也能使 “单向依赖” 成为可能、规避潜在的内存泄漏等问题。




4.LiveData 为什么能解决这三个问题？
特性 1：遵循唯一可信源理念，解决消息分发的一致性问题
    "唯一可信源"理念的设计,通过访问控制权限 来控制消息数据源只能单向发送且对订阅者观察者只读
在 ViewModel 或单例的帮助下,完成单项数据的分发。
LiveData被google被设计为仅限于负责 数据在订阅者生命周期内的被分发，因此除了 setValue / postValue，以及 observe，你再也看不到别的方法，是的，就是这么纯粹。
正因为如此纯粹、甚至无法独当一面，乃至于你不得不借助 ViewModel 或单例，来完成单方向的数据分发 —— 只要你这样做了，Google 的愿望也就达到了。
这样能达到两个目标：
(1)一个是单向依赖：UI -> ViewModel -> Data。（单向依赖的好处，我们在下一篇 ViewModel 的专题中重点介绍）
(2)另一个是 从唯一可信源取材数据源来源，完成数据的分发。
唯一可信源，意味着无论是哪个页面发起的对 “改变状态” 的请求，最终所有页面状态的改变，都是来自可信源统一的决策和分发。
并且 数据一定总是最新的，而不至于取到的是由 ”某个信息滞后的页面“ 所发送的 “过时状态”。
 (2.1)“唯一可信源” 的本质特征和设计原理
  (2.2) “唯一可信源” 在生活中的案例:
      我举个公司群发邮件通知的案例： 人事部（唯一可信源）发送的邮件消息是唯一最可靠的,



特性 2：解决生命周期管理的一致性 和 生命周期安全
 在 Lifecycle 的帮助下，实现 “生命周期管理的一致性”，和 “生命周期安全”。



生命周期安全，啥意思呢？注意观察 observe 方法，第一个参数就是 LifecycleOwner。
让该 LifecycleOwner（例如当前 Fragment 实例/Activity）仅在 onResume 等生命周期节点内，能够接收来自该 LiveData 的单项数据来源分发，从而 有效避免了当前页面已被 Destory时，页面的视图仍在 调用执行 liveData.observe()方法 在回调中被调用获取空数据来源从而空指针异常。
同时又不影响被多个 Fragment 共享的 ViewModel 所持有的该 LiveData(被观察者) 对其他 Fragment 的数据源的分发。
         
             //此时的 被观察者liveData 发送的唯一数据源已经没有了为空, 观察者Observer在进行数据源的接收,就会报空指针异常
		    liveData.observe(this, Observer {
		       tv_live_data_activity.text = it
		          })




特性 3：实现单向依赖，规避内存泄漏
DataBinding 存在的目的是解决 “视图调用的一致性问题”，以及额外支持了 单向依赖、让 视图控制器 不被 ViewModel 所持有，从而规避了一系列的内存泄漏等隐患。
就算不用 DataBinding，我们也能因为 ViewModel 和 LiveData 的配合，而达成 
   (多个订阅者观察者)UI -> ViewModel -> LiveData(唯一可信数据源,被观察者)。
![](https://images.xiaozhuanlan.com/photo/2020/300cf85fbe8c4c2da00cf59c46237c17.png)