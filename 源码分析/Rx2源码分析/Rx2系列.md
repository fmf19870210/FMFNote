

1. 定义

定义:RxJava 是一个 基于事件流、实现异步操作的库(类似于 Android中的 AsyncTask 、Handler作用)



2.Rxjava原理介绍

2.1原理:RxJava原理可总结为：被观察者 （Observable） 通过 订阅（Subscribe） 按顺序发送事件 给观察者 （Observer）(被观察者向观察者传递事件，即观察者模式),
 观察者（Observer） 按顺序接收事件,作出对应的响应动作给被观察者


2.2Rxjava的扩展观察者模式中有4个角色：
角色 	                作用 	                                类比
被观察者（Observable） 	产生/发送事件 	                           顾客
产生事件（Event） 	   被观察者 & 观察者 沟通的载体 	           点菜(菜式)
订阅（Subscribe） 	    连接 被观察者 & 观察者 	                服务员
观察者（Observer） 	    接收事件，响应事件给被观察者 	            厨房

2.3 日常神生活的举例:
 - 一个微信公众号会不断产生新的内容，如果我们读者对这个微信公众号的内容感兴趣，就会订阅这个公众号，当公众号有新内容时，就会推送给我们。我们收到新内容时，如果是我们感兴趣的，就会点进去看下;如果是广告的话，就可能直接忽略掉。这就是我们生活中遇到的典型的观察者模式。
    - 微信公众号就是一个被观察者(Observable)，不断的产生内容（事件），而程序员就是一个观察者(Observer) ，通过订阅（subscribe）就能够接受到微信公众号（被观察者）推送的内容（事件），接收文章(事件)后处理消息【可以看，也可以忽略】。

2.4  适用场景: 数据库的读写、大图片的载入、文件压缩/解压等各种需要放在后台工作的耗时操作



3. Rxjava基本使用
步骤1：创建被观察者 （Observable ）& 生产事件(顾客入饭店 - 坐下餐桌 - 点菜)
步骤2：创建观察者 （Observer ）并 定义响应事件的行为(开厨房 - 确定对应菜式)
步骤3：通过订阅（Subscribe）连接观察者和被观察者(顾客找到服务员 - 点菜 - 服务员下单到厨房 - 厨房烹调)
observable.subscribe(observer);// 或者 observable.subscribe(subscriber)；

Observer 接口:发生的事件类型：
Next事件：当被观察者生产Next事件时,当观察者接收事件时,调用该方法,进行响应。被观察者可以发送无数个Next事件，观察者也可以接受无数个Next事件
Complete事件:当被观察者生产Complete事件&,观察者接收Complete事件时，会调用该方法 进行响应。被观察者发送Complete事件后可以继续发送事件，观察者收到Complete事件后将不会接受其他任何事件
 Error事件:当被观察者生产Error事件,观察者接收error事件,调用该方法。被观察者发送Error事件后，其他事件将被终止发送，观察者收到Error事件后将不会接受其他任何事件




Subscriber接口:发生的事件类型：
onStart()：在还未响应事件前调用，用于做一些初始化工作
Next事件：当被观察者生产Next事件时,当观察者接收事件时,调用该方法,进行响应
Complete事件:当被观察者生产Complete事件&,观察者接收Complete事件时，会调用该方法 进行响应
 Error事件:当被观察者生产Error事件,观察者接收error事件,调用该方法
 unsubscribe()：用于取消订阅。观察者将不再接收 & 响应事件


4. 操作符
 ** 4.1    RxJava创建型操作符 -- 专门创建 被观察者/上游/Observable对象。**
             (观察者：下游，接收事件  完整版本观察者Observer  简化版观察者Consumer)
         
       (1)Observable<T> create():完整创建1个被观察者对象（Observable），使用者自己发射事件
   
      (2)  Observable<T> just(T item1, T item2) :内部自己发射的，单一对象 创建一个被观察者对象Observable，并发送事            件1，发送的事件不可以超过10个                     以上  Observable.just(1, 2, 3,4)
           
					              // 上游
					        Observable.just("A", "B")  // 内部会去发射 A B
					
					        // 订阅
					        .subscribe(
					
					                // 下游
					                new Observer<String>() {
					                    @Override
					                    public void onSubscribe(Disposable d) {
					
					                    }
					
					                    @Override
					                    public void onNext(String s) {
					                        Log.d(TAG, "onNext: " + s);
					                    }
					
					                    @Override
					                    public void onError(Throwable e) {
					
					                    }
					
					                    @Override
					                    public void onComplete() {
					
					                    }
					                }
					        );
					    }



  
     (3)Observable<T> fromArray(T... items)   内部自己发射的，数集对象
        

       String[] stringss = {"1", "2", "3"}; // 内部会去发射 1 2 3

        // 上游 被观察者
        Observable.fromArray(stringss)

        // 订阅
        .subscribe(

                // 下游
                new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );


       (4)Observable<T> empty() 
  
              为什么只支持Object ？
	      	 上游被观察者没有发射有值得事件，下游观察者无法确定类型，默认Object，RxJava泛型 泛型默认类型==Object
     		 做一个耗时操作，不需要任何数据来刷新UI， empty的使用场景之一
              
              // 上游无法指定 事件类型
        Observable.empty() // 内部一定会只调用 发射 onComplete 完毕事件

                // 订阅
                .subscribe(

                 // 下游 观察者
                 new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object integer) {
                        // 没有事件可以接受 为空
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //只接受complete发射的事件数据
                        Log.d(TAG, "onComplete: ");

                        // 隐藏 加载框...
                    }
                }


        (5) Observable<Integer> range(final int start, final int count)  内部自己发射的，start 1 累加   count 5 

           // 上游  range内部会去发射数据事件
        // Observable.range(1, 8) // 1 2 3 4 5 6 7 8  从1开始加 数量共8个
        Observable.range(80, 5) // 80开始  80 81 82 83 84  加    数量共5个

        // 订阅
        .subscribe(

           // 下游
           new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
            }
        });
  

  ** 4.2  RxJava变换型操作符。**
        上游  Observable 被观察者 ------->    数据变换操作(数据往右边流向的时候，进行变换)  ---------->  下游  Observer 观察者
    
  **(1)map() **   
           把上一层Int  Int变换String   观察者接受String类型数据。
        
  ![](https://upload-images.jianshu.io/upload_images/944365-a9c0b5eb2cc573d6.png)
![](https://upload-images.jianshu.io/upload_images/944365-dc0e57fb348f6eab.png)
 
 
        // 上游
        Observable.just(1) // 发射 数据 1

        // 发送的数据在上游和下游之间 变换 1-->"1"
        .map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                // 进行变化操作 1-->"1"     返回 String "1"
                Log.d(TAG, "map1 apply: " + integer);

                return "【" + integer + "】";
            }
        })
                
        .map(new Function<String, Bitmap>() {
            @Override
            public Bitmap apply(String s) throws Exception {
                // s == 【" + integer + "】
                Log.d(TAG, "map2 apply: " + s);
                // 进行变化操作  "1"-->Bitmap    返回  Bitmap
                return Bitmap.createBitmap(1920, 1280, Bitmap.Config.ARGB_8888);

                // return null; // 如果返回null，下游无法接收
            }
        })

        // 订阅
        .subscribe(

                // 下游
                new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap string) {
                        //下游 接受变换后的数据 返回的数据  string=android.graphics.Bitmap@fd94983
                        Log.d(TAG, "下游 onNext: " + string);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }

        );


         Observable.create(new ObservableOnSubscribe<Integer>() {
                     @Override
                     public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                         emitter.onNext(1);
                         emitter.onNext(2);
                         emitter.onNext(3);

                     }
                     // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
                 }).map(new Function<Integer, String>() {
                               @Override
                               public String apply(Integer integer) throws Exception {}
                            }).subscribe(new Consumer<String>() {

                           // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
                           @Override
                          public void accept(String s) throws Exception {
                        Log.d(TAG, s);}});





   **(2) flatMap()** 
   Observable<R> flatMap()   把上一层Int  Int变换ObservableSource<String>{还可以再次发射多次事件}   观察者String类型。 不排序的
 ![](https://upload-images.jianshu.io/upload_images/944365-a6f852c071db2f15.png)

         // 上游
        Observable.just(1111)

        // 变换操作符
        .flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final Integer integer) throws Exception {

                // integer == 111

                //转换后的对象中间者 ObservableSource == 可以再次手动发送多个事件
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext(integer + "flatMap变换操作符");
                        e.onNext(integer + "flatMap变换操作符");
                        e.onNext(integer + "flatMap变换操作符");
                    }
                });
            }
        })
           // 订阅
        .subscribe(

                // 下游
                new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                     //下游 接受变换后的数据
                        Log.d(TAG, "下游接收 变换操作符 发射的事件 accept: " + string);
                    }
        });


   
                ---------

          // 上游 发射多个事件
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("步惊云"); // String
                e.onNext("雄霸");
                e.onNext("李四");
                e.onComplete();
            }
        })

        .flatMap(new Function<String, ObservableSource<?>>() { //
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add(s + " 下标：" + (1 + i));
                }
                return Observable.fromIterable(list).delay(5, TimeUnit.SECONDS); // 创建型操作符，创建被观察者
            }
        })

        // 订阅
        .subscribe(/*new Consumer<Object>() { // 下游
            @Override
            public void accept(Object s) throws Exception {
                Log.d(TAG, "下游 accept: " + s);
            }
        }*/

                new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "下游 onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }

        );
       -----------
          // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用flatMap（）变换操作符
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

      ------------
                            // （初始被观察者）切换到IO线程进行网络请求1
                             observable1.subscribeOn(Schedulers.io())  
                            .observeOn(AndroidSchedulers.mainThread())  // （新观察者）切换到主线程 处理网络请求1的结果
                            .doOnNext(new Consumer<Translation1>() {
                             @Override
                             public void accept(Translation1 result) throws Exception {
                                 Log.d(TAG, "第1次网络请求成功");
                                 result.show();
                                 // 对第1次网络请求返回的结果进行操作 = 显示翻译结果
                             }
                         })
                       .observeOn(Schedulers.io())                 // （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求
                                                                // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
                                                                // 但对于初始观察者，它则是新的被观察者
                    .flatMap(new Function<Translation1, ObservableSource<Translation2>>() { // 作变换，即作嵌套网络请求
                        @Override
                        public ObservableSource<Translation2> apply(Translation1 result) throws Exception {
                            // 将网络请求1转换成网络请求2，即发送网络请求2
                            return observable2;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())  // （初始观察者）切换到主线程 处理网络请求2的结果
                    .subscribe(new Consumer<Translation2>() {
                        @Override
                        public void accept(Translation2 result) throws Exception {
                            Log.d(TAG, "第2次网络请求成功");
                            result.show();
                            // 对第2次网络请求返回的结果进行操作 = 显示翻译结果
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            System.out.println("登录失败");
                        }
                    });
                     }

 
 

  


**4.3  过滤操作符**

       上游:发射所有的数据  ------->    过滤操作(往右边流向的时候，进行过滤不符合要求的数据)  ---------->  下游:接收输出符合要求的数据
         (1)filter  如果是true，全部都发射给下游。      如果是false全部都不发射给下游，
						                        // 上游
						        Observable.just("三鹿", "合生元", "飞鹤")
						
						        .filter(new Predicate<String>() {
						            @Override
						            public boolean test(String s) throws Exception {
						                // return true; // 不去过滤，默认全部都会打印
						                // return false; // 如果是false 就全部都不会打印
						
						                if ("三鹿".equals(s)) {
						                    return false; // 不合格
						                }
						
						                return true;
						            }
						        })
						
						        // 订阅
						        .subscribe(new Consumer<String>() { // 下游
						            @Override
						            public void accept(String s) throws Exception {
						                Log.d(TAG, "accept: " + s);
						            }
						        });
						    }


   (2)take( ：上游只有在定时器运行基础上 加入take过滤操作符，才有take过滤操作符的价值。
           // 定时器 运行   只有再定时器运行基础上 加入take过滤操作符，才有take过滤操作符的价值

        // 上游
        Observable.interval(2, TimeUnit.SECONDS)

                // 增加过滤操作符，停止定时器
                .take(8) // 执行次数达到8 停止下来

                .subscribe(new Consumer<Long>() { // 下游
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept: " + aLong);
                    }
                });

  (3)distinct 对上游的数据源的重复数据进行过滤。 下游输出不重复的数据
                       Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(4);
                e.onComplete();
            }
        })

        .distinct() // 过滤重复 发射的事件

        .subscribe(new Consumer<Integer>() { // 下游 观察者
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer); // 事件不重复 输出不重复的数据 1 2 3 4
            }
        });


 (4)elementAt 指定发射事件内容，如果无法指定，有默认的事件。
              // 上游
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("九阴真经");
                e.onNext("九阳真经");
                e.onNext("易筋经");
                e.onNext("神照经");
                e.onComplete(); 
            }
        })

        // 过滤操作符
        .elementAt(100, "默认经") // 指定下标输出 事件

        // 订阅
        .subscribe(new Consumer<String>() { // 下游
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });





**4.4 条件 / 布尔操作符**
上游  ------->    条件操作(往右边流向的时候，条件判断)  ---------->  下游

判断被观察者（Observable）发送的事件是否符合条件,符合条件的事件发送给观察者接收,不符合条件的不发送。
     
 ** Single<Boolean> all( ) :  全部为true，才是true，只要有一个为false，就是false.**
            String v1 = "1";
	        String v2 = "2";
	        String v3 = "3";
	        String v4 = "cc";
	         // 上游
	        Observable.just(v1, v2, v3, v4) // RxJava                                                                                                                                                                            2.X 之后 不能传递null，否则会报错
	
	        .all(new Predicate<String>() {
	            @Override
	            public boolean test(String s) throws Exception {
	                return !s.equals("cc"); // 如果s不等于cc，就是true
	            }
	        })
	
	        .subscribe(new Consumer<Boolean>() { // 下游
	            @Override
	            public void accept(Boolean s) throws Exception {
	                Log.d(TAG, "accept: " + s);
	            }
	        });
	    }





**Single<Boolean> contains( )   是否包含**
		 Observable.just("JavaSE", "JavaEE", "JavaME", "Android", "iOS", "Rect.js", "NDK")

        .contains("C")     // 是否包含了 Android，条件是否满足

        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }
			


**Single<Boolean> any( )  全部为 false，才是false， 只要有一个为true，就是true 如果使用了条件操作符，下一层，接收的类型 就是条件类型(Boolean)**
         		
				 Observable.just("JavaSE", "JavaEE", "JavaME", "Android", "iOS", "Rect.js", "NDK")

                .any(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.equals("Android");
                    }
                })     // 是否包含了 Android，条件是否满足 包含 返回 true

                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean s) throws Exception {
                        Log.d(TAG, "accept: " + s); //下游输出  true
                    }
                });



**4.5  合并型操作符。两个或者多个 被观察者 合并。**

**4.5.1.startWith，concatWith ：先创建被观察者，然后再组合其他的被观察者，然后再订阅**
    Observable<T> startWith(ObservableSource<? extends T> other)
    startWith 合并操作符, 被观察者1.startWith(被观察者2) 先执行 被观察者2 里面发射的事件，然后再执行 被观察者1 发射的事件
    Observable<T> concatWith(ObservableSource<? extends T> other)
    concatWith 合并操作符, 被观察者1.concatWith(被观察者2) 先执行 被观察者1 里面发射的事件，然后再执行 被观察者2 发射的事件
   startWith 先执行 startWith 括号里面的被观察者
    concatWith 后执行 concatWith 括号里面的被观察者

			 // 上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // todo 2
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
        .startWith(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // todo 1
                e.onNext(10000);
                e.onNext(20000);
                e.onNext(30000);
                e.onComplete();
            }
        }))
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
            }
        });


            // 上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // todo 1
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .concatWith(Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        // todo 2
                        e.onNext(10000);
                        e.onNext(20000);
                        e.onNext(30000);
                        e.onComplete();
                    }
                }))
                .subscribe(new Consumer<Integer>() { // 下游
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });


**4.5.2.concat/merge/zip：直接合并多个被观察者(没有创建之说)，然后订阅**
 

  concat :合并操作符 的特性：最多能够合并四个被观察者，按照我们存入的顺序执行 合并成一个被观察者 然后发送给观察者
   Observable<T> concat(
               ObservableSource source1, ObservableSource source2,
               ObservableSource source3, ObservableSource source4)

 merge 合并操作符（演示并列的执行，所以学了intervalRange） 最多有四个被观察者进行合并成一个被观察者 ，四个被观察者并列执行

 zip 合并操作符，最多可以9个被观察者进行合并：多个被观察者发射的事件，多个被观察者之间的数据需要相互对应 ,如果不对应，会被忽略的，

               // 上游 被观察者
        Observable.concat(

                Observable.just("1") // todo 1
                ,
                Observable.just("2") // todo 2
                ,
                Observable.just("3") // todo 3
                ,
                Observable.create(new ObservableOnSubscribe<String>() { // todo 4
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("4");
                        e.onComplete();
                    }
                })

        )
        .subscribe(new Consumer<String>() { // 下游 观察者
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });



            // 被观察者1
        Observable observable1 = Observable.intervalRange(1, 5, 1,2, TimeUnit.SECONDS);//1 2 3 4 5
        // 被观察者2
        Observable observable2 = Observable.intervalRange(6, 5, 1,2, TimeUnit.SECONDS); // 6 7 8 9 10
        // 被观察者3
        Observable observable3 = Observable.intervalRange(11, 5, 1,2, TimeUnit.SECONDS); // 11 12 13 14 15

        // 上游
        Observable.merge(observable1, observable2, observable3) // 合并成一个 被观察者

        .subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

                // 被观察者1  1
                // 被观察者2  6
                // 被观察者3  11

                Log.d(TAG, "accept: " + o);
            }
        });
    }



				 // 课程 被观察者1
        Observable observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("英语"); // String
                e.onNext("数学");
                e.onNext("政治");
                e.onNext("物理");  // 被忽略
                e.onComplete();
            }
        });

        // 分数 被观察者2
        Observable observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(85); // Integer
                e.onNext(90);
                e.onNext(96);
                e.onComplete();
            }
        });

        // 被观察者1+ 被观察者2--> 合拼后的被观察者
        //BiFunction<T1, T2, R>  类型   T1 String(被观察者1类型), T2 Integer(被观察者2类型),  R StringBuffer(被观察者1,被观察者2合拼后返回的类型)
        Observable.zip(observable1, observable2, new BiFunction<String, Integer, StringBuffer>() {
            @Override
            public StringBuffer apply(String string, Integer integer) throws Exception {
                return new StringBuffer().append("课程" + string).append("==").append(integer+"");
            }
        })
          //观察者
        .subscribe(   new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: 准备进入考场，考试了....");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "onNext: 考试结果输出 " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: 考试全部完毕");
                    }
                }
        );
    }



 **4.5.3  RxJava异常处理操作符。**
1.RxJava中是不标准的throw new IllegalAccessError("我要报错了");
2. RxJava标准的e.onError(XXX);
3.onErrorReturn能够接收e.onError，如果接收到异常，会中断上游后续发射的所有事件
并且可以给下游返回一个 标识400,   throw new  XXX 拦截不到，整个程序奔溃、、、



4.onErrorResumeNext能够接收e.onError，可以返回被观察者（被观察者可以再次发射多次事件给 下游），
  throw new  XXX 拦截不到，整个程序奔溃


5.onExceptionResumeNext 能在发生异常的时候，扭转乾坤，能够处理 throw new  XXX，可以真正的让App不奔溃,但程序确实是有异常的

6.retry 重试操作符 异常处理操作符中
   retry return false; 代表不去重试
   return true; 不停的重试，
  ** times 只让其重试多少次**
     retry(long times, Predicate<? super Throwable> predicate)


 **打印记录重试了多少次，计数**
         .retry(new BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(Integer integer, Throwable throwable) throws Exception {
                Log.d(TAG, "retry: 已经重试了:" + integer + "次  e：" + throwable.getMessage());
                return true; // 重试
            }
        })

 
 
Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
                 }
               })
                .retry() // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送
                .retry(3) // 设置重试次数 = 3次
                .retry(new Predicate<Throwable>() {// 拦截错误后，判断是否需要重新发送请求
                    @Override
                    public boolean test(@NonNull Throwable throwable) throws Exception {
                        // 捕获异常
                        Log.e(TAG, "retry错误: "+throwable.toString());

                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                        //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                        return true;
                    }
                })
                //返回true  flase  判断是否需要重新发送请求
              .retry(3, new Predicate<Throwable>() {
                    @Override
                    public boolean test(@NonNull Throwable throwable) throws Exception {
                        // 捕获异常
                        Log.e(TAG, "retry错误: "+throwable.toString());

                        //返回false = 不重新重新发送数据 & 调用观察者的onError（）结束
                        //返回true = 重新发送请求（最多重新发送3次）
                        return true;
                    }
                })





                .subscribe(new Observer<Integer>() {
                     @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });



(4)
 // 通过retryWhen（）进行重试,只有异常才会回调retryWhen（）进行重试
                                                          .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                                                              @Override
                                                              public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                                                                  // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型

                                                                  return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                                                                      @Override
                                                                      public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                                                          // 输出异常信息
                                                                          LogUtils.d("发生异常: "+ throwable.toString());
                                                                            /**
                                                                               * 限制重试次数
                                                                               * 即，当已重试次数 < 设置的重试次数，才选择重试
                                                                               */
                                                                              if (currentRetryCount < maxConnectCount){
                                                                                  // 记录重试次数
                                                                                  currentRetryCount++;
                                                                                  LogUtils.d("重试次数 = " + currentRetryCount);
                                                                                  waitRetryTime = 1000 + currentRetryCount* 1000;
                                                                                  return Observable.just(1).delay(waitRetryTime, TimeUnit.MILLISECONDS);
                                                                              }else{
                                                                                  // 若重试次数已 > 设置重试次数，则不重试
                                                                                  // 通过发送error来停止重试（可在观察者的onError（）中获取信息）
                                                                                  return Observable.error(new Throwable("重试次数已超过设置次数 = " +currentRetryCount  + "，即 不再重试"));
                                                                              }

                                                                      }
                                                                  });
                                                              }
                                                          })




**4.5.4  RxJava线程切换 **


 Rx调度/切换: Scheduler(线程调度器)

1 主要作用:指定 被观察者Observable/ 观察者Observer的工作线程类型。


2 为什么要进行RxJava线程控制（调度 / 切换）

若被观察者 （Observable） / 观察者（Observer）在主线程被创建，那么他们的工作（生产事件 / 接收& 响应事件）就会发生在主线程

因为创建被观察者 （Observable） / 观察者（Observer）的线程 = 主线程,生产事件 / 接收& 响应事件都发生在主线程,会导致ANR,

所以为了解决ANR,  实现真正的异步操作, 使用 RxJava进行 线程控制（也称为调度 / 切换）(subscribeOn（） & observeOn（）),
使用线程调度器（ Scheduler ，指定 被观察者 （Observable） / 观察者（Observer） 的工作线程类型(在主/子线程)
 让被观察者Observable在子线程中生产事件,观察者Observer在主线程接收&响应事件。


 
 3  异步线程区域 
        Schedulers.io() ：代表io流操作，网络操作，文件流，耗时操作
        Schedulers.newThread()    ： 比较常规的，普普通通 用的比较少
        Schedulers.computation()  ： 代表CPU 大量计算 所需要的线程
         AndroidSchedulers.mainThread()  ： 专门为Android main线程量身定做的 主线程


4 线程控制（也称为调度 / 切换）:subscribeOn（） & observeOn（）
 **subscribeOn(Schedulers.io())** 给上游配置异步线程，给上游分配多次切换线程，只会在第一次切换有效，后面的不切换了 无效 （忽略）
**observeOn(AndroidSchedulers.mainThread()) **  给下游配置 安卓主线程
   给下游分配多次，每次都会去切换,当切换到最后一次为什么线程,下游就为什么线程

 Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "上游 subscribe: " + Thread.currentThread().getName());
                e.onNext("");
            }
        }
                .subscribeOn(Schedulers.io()) // todo 给上游配置异步线程    // 给上游分配多次，只会在第一次切换，后面的不切换了
                .subscribeOn(AndroidSchedulers.mainThread()) // 被忽略
                .subscribeOn(AndroidSchedulers.mainThread()) // 被忽略
                .subscribeOn(AndroidSchedulers.mainThread()) // 被忽略
                .subscribeOn(AndroidSchedulers.mainThread()) // 被忽略
   				.observeOn(AndroidSchedulers.mainThread()) // todo 给下游配置 安卓主线程    // 给下游分配多次，每次都会去切换,当切换到最后一次为什么线程,下游就为什么线程
                .observeOn(AndroidSchedulers.mainThread()) // 切换一次线程
                .observeOn(AndroidSchedulers.mainThread()) // 切换一次线程
                .observeOn(AndroidSchedulers.mainThread()) // 切换一次线程
                .observeOn(Schedulers.io()) // 切换一次线程
       
          
          .subscribe(new Consumer<String>() { // 下游简化版   如果不配置 默认就是主线程main
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "下游 subscribe: " + Thread.currentThread().getName());
            }
        });


5.RX的同步 与异步
默认情况下     上游和下游都是main线程的情况下(如果不配置异步线程),同步的想象,上游发一次，下游接收一次，上游发一次，下游接收一次，上游发一次，下游接收一次
上游分配 异步线程   给下游分配 主线程.配置好异步线程，就是异步的表现


6.使用RX异步下载图片的demo
          // 起点

        // 上游 被观察者 Observable
        Observable.just(PATH)  // 内部发射

        // String Path  变换  Bitmap
        .map(new Function<String, Bitmap>() {
            @Override
            public Bitmap apply(String s) throws Exception {

                try {
                    Thread.sleep(2000);

                    URL url = new URL(PATH);
                    URLConnection urlConnection = url.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                    httpURLConnection.setConnectTimeout(5000);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (HttpURLConnection.HTTP_OK == responseCode) {
                        Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        return bitmap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        })

        .map(new Function<Bitmap, Bitmap>() {
            @Override
            public Bitmap apply(Bitmap bitmap) throws Exception {
                // 给图片加水印
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setTextSize(30);
                Bitmap bitmapSuccess = drawTextToBitmap(bitmap, "同学们大家好", paint, 60, 60);
                return bitmapSuccess;
            }
        })

        // 比如：增加一个 日志纪录功能，只需要添加要给 变换操作符
        .map(new Function<Bitmap, Bitmap>() {
            @Override
            public Bitmap apply(Bitmap bitmap) throws Exception {
                Log.d(TAG, "apply: 下载的Bitmap 是这个样子的" + bitmap);
                return bitmap;
            }
        })

        .subscribeOn(Schedulers.io()) // todo  给上游分配 异步线程
        .observeOn(AndroidSchedulers.mainThread()) // todo  给下游分配 主线程

        .subscribe(new Observer<Bitmap>() { // 下游
            @Override
            public void onSubscribe(Disposable d) {
                progressDialog = new ProgressDialog(MainActivity8.this);
                progressDialog.setMessage("RxJava下载图片中..");
                progressDialog.show();
            }

            @Override
            public void onNext(Bitmap bitmap) {
                if (imageView != null)
                    imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Throwable e) { // 发生异常
               //  if (imageView != null)
                   //  imageView.setImageResource(R.mipmap.ic_launcher); // 下载错误的图片
            }

            @Override
            public void onComplete() { // 终点
                if (progressDialog != null)
                    progressDialog.dismiss();
            }
        });
    }


    //图片上绘制文字
    private Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }


**4.5.5 RxJava背压模式**

 (1)背压模式的由来：
 RxJava1.X的时候，还没有背压模式， 我们的上游不停的发射，我们的下游处理不过来，就会照成内存泄漏
 RxJava2.X之后，增加背压模式，所以上游被观察者不用Observable  用Flowable（解决背压）

 


 (2)背压出现原因:

被观察者 发送事件速度太快，而观察者 来不及接收所有事件，从而导致观察者无法及时响应 / 处理所有发送过来事件的问题，最终导致缓存区溢出、事件丢失 & OOM,
当上下游在不同的线程中，通过Observable发射，处理，响应数据流时，如果上游发射数据的速度快于下游接收处理数据的速度，这样对于那些没来得及处理的数据就会造成积压，这些数据既不会丢失，也不会被垃圾回收机制回收，而是存放在一个异步缓存池中，如果缓存池中的数据一直得不到处理，越积越多，最后就会造成内存溢出，这便是响应式编程中的背压（backpressure）问题


  (3)背压的原理

  解决被观察者发送事件速度 与观察者接收事件速度 不匹配的问题
  控制被观察者发送事件速度:被观察者   根据 观察者接收事件速度的能力    来控制发送事件的速度(反馈控制)
  控制观察者接收事件速度: 让观察者根据自己的实际情况来接收事件(响应式来取事件)



  (4)被压的一个案例:
        快速点击按钮10次,按钮只有2次响应了,其中的8次没有响应,事件积压没有响应, 这就是被压。



  (5) Rx1.0的 旧实现Observable被观察者为什么不能很好解决背压问题？
    原因:  Rx1.0的  Observable被观察者的内部队列存储数据大小=16, 
           Observable发送的事件很容易超过自身的存储大小16,造成发送的事件积压，导致报错。
           RxJava2.X之后，增加背压模式，所以上游被观察者不用Observable  用Flowable（解决背压）
   
   ![](https://upload-images.jianshu.io/upload_images/944365-2a2dac291a1ec818.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200)

   ![](https://upload-images.jianshu.io/upload_images/944365-30fdc84d48e39456.png)



 6.6 解决被压的方案:
 
  (1)控制 观察者接收事件 的速度:不管被观察者发送多少事件,观察者只根据自身的情况来接收事件。

 观察者不接收事件的情况下，被观察者继续发送事件 & 存放到缓存区；再按需取出
 观察者不接收事件的情况下，被观察者继续发送事件至超出缓存区大小（128）
  被观察者在发送1个事件后，必须等待观察者接收后，才能继续发下1个事件  
  控制 被观察者发送事件 的速度: 通过FlowableEmitter.requested()控制流速。

(2)采用 Flowable 实现 背压策略,被观察者的一种新实现实例类，背压策略实现的承载者 Flowable
     Flowable  被观察者（Observable）的一种新实现(类似于 Observable)
     Flowable  也叫 “非阻塞式背压” 策略  流动的
     Flowable 的缓存区大小128  由buffersize()决定的,如果发送数据存储到缓冲区超过128,就会OOM

 (3) Flowable的 背压策略模式BackpressureStrategy的类型:
![](https://upload-images.jianshu.io/upload_images/944365-47b55edec299faea.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200)

具体体现:当缓存区大小存满（默认缓存区大小 = 128）、被观察者仍然继续发送下1个事件时.
发送事件速度 ＞ 接收事件 速度，即流速不匹配



  
模式1：BackpressureStrategy.ERROR
处理方式：直接抛出异常MissingBackpressureException
少用  上游不停的发射大量事件，下游阻塞了 处理不过来，放入缓存池(max 128)，如果池子满了(max 128)，超过128,就会抛出异常


模式2：BackpressureStrategy.MISSING
处理方式：友好提示：缓存区满了


模式3：BackpressureStrategy.BUFFER
处理方式：将缓存区大小设置成无限大
  最常用的   上游不停的发射大量事件，下游阻塞了 处理不过来，放入缓存池，”等待“下游来接收事件处理


模式4： BackpressureStrategy.DROP
处理方式：超过缓存区大小（128）的事件丢弃



模式5：BackpressureStrategy.LATEST
处理方式：只保存最新（最后）事件，超过缓存区大小（128）的事件丢弃



(4) 被压的同步 异步的问题
	同步的:
	  上游下游是同步的 需要等待 如果下游调用执行Subscription s.request()方法处理事件后，然后上游才能再发射后面的事件，
	    如果下游没有调用执行Subscription s.request(),接收处理事件,就会抛出异常 create: could not emit value due to lack of requests
    异步的:
	上游下游是异步的  上游可以不停的发射，  下游调用执行 subscription.request()方法; 下游就可以取出事件来处理 是ok的
	  如果 下游不执行此s.request(),不会发生异常（上游不会等待下游,一直发送）不会发生异常。

(5)一旦下游处理了一次上游的事件，缓存池 - 1
 

(6) Demo案例:

 
/**
  * 步骤1：创建被观察者 =  Flowable
  */
Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR);// 需要传入背压参数BackpressureStrategy，


/**
   * 步骤2：创建观察者 =  Subscriber
   */

Subscriber<Integer> downstream = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                // 对比Observer传入的Disposable参数，Subscriber此处传入的参数 = Subscription
                // 相同点：Subscription具备Disposable参数的作用，即Disposable.dispose()切断连接, 同样的调用Subscription.cancel()切断连接
                // 不同点：Subscription增加了void request(long n)
                Log.d(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);
               // 关于request()下面会继续详细说明
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };



    upstream.subscribe(downstream);





 
// 1. 创建被观察者Flowable
    Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                // 一共发送4个事件
                Log.d(TAG, "发送事件 1");
                emitter.onNext(1);
                Log.d(TAG, "发送事件 2");
                emitter.onNext(2);
                Log.d(TAG, "发送事件 3");
                emitter.onNext(3);
                Log.d(TAG, "发送事件 4");
                emitter.onNext(4);
                Log.d(TAG, "发送完成");
                // 调用emitter.requested()获取当前观察者需要接收的事件数量
                 Log.d(TAG, "观察者可接收事件个数" + emitter.requested());
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR) //采用背压策略模式
        .subscribeOn(Schedulers.io()) // 设置被观察者在io线程中进行
                .observeOn(AndroidSchedulers.mainThread()) // 设置观察者在主线程中进行
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        // 对比Observer传入的Disposable参数，Subscriber此处传入的参数 = Subscription
                        // 相同点：Subscription参数具备Disposable参数的作用，即Disposable.dispose()切断连接, 同样的调用Subscription.cancel()切断连接
                        // 不同点：Subscription增加了void request(long n)

                        s.request(3);
                        // 作用：决定观察者能够接收多少个事件
                        // 如设置了s.request(3)，这就说明观察者能够接收3个事件（多出的事件存放在缓存区）
                        // 官方默认推荐使用Long.MAX_VALUE，即s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });




    Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                // todo 1 上游不停的发射大量事件
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    e.onNext(i); // todo 1
                }
                e.onComplete();
            }
        },
      BackpressureStrategy.BUFFER )
      
         .subscribeOn(Schedulers.io()) // 给上游分配异步线程
         .observeOn(AndroidSchedulers.mainThread())     // 给下游分配 主线程
         .subscribe(
                 // 完整版本的下游 观察者
                new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                       
                        // 如果同步的 不执行此s.request();，（等待下游，发现下游没有去处理）会抛出异常， 外界在调用subscription.request(10); 无效果
                        // 如果是异步的，不执行此s.request();，不会发生异常（上游不会等待下游）不会发生异常， 外界在调用subscription.request(10); 是ok的
                        // s.request(5); //这里只请求输出 5次，给下游打印
                        // s.request(100);  // 这里只请求输出 100次，给下游打印
                        // s.request(500); // 只请求给下游输出 500个事件，给下游打印
                        // s.request(128); // 取出129给事件，给下游
                         s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {

                        // todo 2 下游阻塞了 处理不过来
                        try {
                            Thread.currentThread().sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        // TODO 一旦下游处理了一次上游的事件，缓存池 - 1
                        Log.d(TAG, "onNext: " + integer);
                    }

                    // onError: create: could not emit value due to lack of requests  上游还有剩余的事件，无法处理，因为没有去请求
                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                }
        );
        )
 




(7) 

如果我们会使用Observable， 那么一定会使用Flowable
Flowable的设计，是按照Observable 依葫芦画瓢来设计Flowable，所以使用才一摸一样，只不过类名不同而已， Flowable还增加了背压模式
 
1.上游Observable<--->下游Observer， 上游Flowable<--->下游Subscriber  对应关系 ？
2.Observable的设计和  Flowable一致的，在Observable的基础上 增加了一套Flowable的代码，而且增加的时候 依葫芦画瓢的，Flowable增加了背压模式
3.Observable--Observer下游 -- onSubscribe(Disposable d) 切断的是下游的水管，上游还会继续发射，只是下游无法接收了
4.Flowable---Subscriber下游 -- onSubscribe(Subscription s) 取出（s.request(5)）事件 给下游接收使用


    Observable.just("李四","张三", "王五")
                .subscribe(new Observer<String>() { // 下游 Observer 完整版
                    @Override
                    public void onSubscribe(Disposable d) {
                        // 切断下游，注意：切断的是下游的水管，上游还会继续发射，只是下游无法接收了
                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

  
     Flowable.just("李四","张三", "王五")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1); // 取出来给下游接收
                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });







**RxJava配合Retrofit。**


1.OkHttp 请求网络 （Retorfit）
2.Retorfit 返回一个结果 （Retorfit） --- Observable
3.最终的结果 是RxJava中的 被观察者 上游 Observable
4.一行代码写完需求流程： 从上往下
   1.请求服务器，执行注册操作（耗时）切换异步线程
   2.更新注册后的所有 注册相关UI - main  切换主线程
   3.请求服务器，执行登录操作（耗时）切换异步线程
   4.更新登录后的所有 登录相关UI - main  切换主线程

5.看RxJava另外一种的执行流程
  初始点 开始点 订阅
  1.onSubscribe
  2.registerAction(new RegisterRequest())
  3..doOnNext 更新注册后的 所有UI
  4.flatMap执行登录的耗时操作
  5.订阅的观察者 下游 onNext 方法，更新所有登录后的UI
  6.progressDialog.dismiss()


               


        // 分开写
        /**
         * 1.请求服务器注册操作
         * 2.注册完成之后，更新注册UI
         */
 

        // 1.请求服务器注册操作
        MyRetrofit.createRetrofit().create(IRequestNetwork.class) // IRequestNetwork
                // IRequestNetwork.registerAction
                .registerAction(new RegisterRequest())  // Observable<RegisterResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                 // 2.注册完成之后，更新注册UI
                 .subscribe(new Consumer<RegisterResponse>() { // 下游 简化版
                     @Override
                     public void accept(RegisterResponse registerResponse) throws Exception {
                         // 更新注册相关的所有UI
                         // .....
                     }
                 });




          // 3.马上去登录服务器操作
         MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .loginAction(new LoginRequest())  // Observable<LoginResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程

                // 4.登录完成之后，更新登录的UI
                .subscribe(new Consumer<LoginResponse>() { // 下游 简化版
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        // 更新登录相关的所有UI
                        // .....
                    }
                });



















             /**
	         * 一行代码 实现需求
	         * 需求：
	         *  * 1.请求服务器注册操作
	         *  * 2.注册完成之后，更新注册UI
	         *  * 3.马上去登录服务器操作
	         *  * 4.登录完成之后，更新登录的UI
	         */
          progressDialog = new ProgressDialog(this);
         progressDialog.setMessage("正在执行中...");
	       

         MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                //  1.请求服务器注册操作  // todo 第二步 请求服务器 注册操作
                .registerAction(new RegisterRequest()) // Observable<RegisterResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                // 2.注册完成之后，更新注册UI

                /**
                 *  这样不能订阅，如果订阅了，就无法执行
                 *      3 马上去登录服务器操作
                 *      4.登录完成之后，更新登录的UI
                 *
                 *  所以我们要去学习一个 .doOnNext()，可以在不订阅的情况下，更新UI
                 */

                  .doOnNext(new Consumer<RegisterResponse>() { // 简单版本的下游
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // todo 第三步 更新注册相关的所有UI
                        // 更新注册相关的所有UI
                        tv_register_ui.setText("xxx");
                        // .......
                    }
                })
                 // 3.马上去登录服务器操作 -- 耗时操作
                .subscribeOn(Schedulers.io()) // todo 分配异步线程
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        // 还可以拿到 注册后的响应对象RegisterResponse
                        // 执行耗时操作
                        // 马上去登录服务器操作 -- 耗时操作
                        Observable<LoginResponse> observable = MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                                .loginAction(new LoginRequest());  // todo 第四步 马上去登录服务器操作 -- 耗时操作
                        return observable;
                    }
                })

                // 4.登录完成之后，更新登录的UI
                .observeOn(AndroidSchedulers.mainThread()) // // todo 给下游切换 主线程
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // todo 第一步
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        // 更新登录相关的所有UI
                        // todo 第五步 更新登录相关的所有UI
                        tv_login_ui.setText("xxxx");
                        // ...........
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        // todo 第六步
                        progressDialog.dismiss(); // 结束对话框 ，整个流程完成
                    }
                });
                  

              



 **泛型 Java**
由于RxJava大量的使用到了泛型，所有学习
如果我们不指定泛型类型，默认就是Object，Object的扩展集
1.? super F   下限： 限制最低 子类 类型F (Student)  类型不能在低，不能是 F(Student)  的子类了
       F or F 所有父类 都可以，所以属于把下面的类型限限制了，下面的类型不能低于F，不能是F的子类，否则编译不通过。

2.? extends F 上限：限制最高顶层父类类型是F(Person) (不能比这更高了)， F (Person) or F 所有子类(stdutent worker) 都可以，所有属于把上面的类型给限制了，
                  上面的类型不能高于F，不能是F的父类，否则编译不通过。
  一句话记住（? super F: F 或者 F的所有父类 都可以，   ? extends F: F 或者 F的所有子类都可以）
3.可写模式<？super F> 可写，不完全可读
       Test<? super Person> test = null;
        test.add(new Person()); // 可写
        test.add(new Student()); // 可写
        test.add(new Worker()); // 可写


4.可读模式<? extends F> 可读，不可写

        Test<? extends Person> test1 = null;
        test1.add(new Person()); // 不可写
        test1.add(new Student()); // 不可写
        test1.add(new Object()); // 不可写
        test1.add(new Worker()); // 不可写          