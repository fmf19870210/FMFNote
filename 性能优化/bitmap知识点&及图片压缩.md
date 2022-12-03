 






一:什么是bitmap
位图文件（Bitmap），扩展名可以是.bmp或者.dib。位图是Windows标准格式图形文件，它将图像定义为由点（像素）组成，每个点可以由多种色彩表示，包括2、4、8、16、24和32位色彩。位图文件是非压缩格式的，需要占用较大存储空间。



二:计算bitmap占用内存大小

  		
       2.1  一个像素占用多大内存
			ARGB_8888: 每个像素4字节, A(透明度)=8位,R(红色)=8位,G(绿色)=8位 ,B(蓝色)=8位,共32位
			RGB_565:每个像素2字节,R=5位,G=6位,B=5位,共16位. 
                             如果你不需要透明度,选择RGB_565,可以减少一半的内存占用。
      		ARGB_4444: 每个像素2字节,ARGB每个值是4位, 共16位


bitmap内存大小 = 图片长度 x 图片宽度 x 单位像素占用的字节数
       起决定因素就是最后那个参数了，Bitmap'常见有2种编码方式：ARGB_8888和RGB_565，ARGB_8888每个像素点4个byte，RGB_565是2个byte，一般都采用ARGB_8888这种。
      


		 那么常见的1080*1920的图片内存占用就是：
		1920 x 1080 x 4(ARGB_8888) = 7.9M  
		 1920 x 1080 x 2(RGB_565) = 7.9M





三:Bitmap缓存原理

 LruCache原理 
LruCache是个泛型类，内部采用LinkedHashMap来实现缓存机制，它提供get方法和put方法来获取缓存和添加缓存，其最重要的方法trimToSize是用来移除最少使用的缓存和使用最久的缓存，并添加最新的缓存到队列中







四:Bitmap的压缩方式


		1.质量压缩(不会对内存产生影响)(Bitmap.compress()):
		
      	1.1 它是在保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的，不会减少图片的像素。
		压缩的图片文件大小会变小，但是解码成bitmap后占得内存是不变的
         bitmap图片的大小不会改变,
         bytes.length是随着quality变小而变小的.
	  

        /**  
	       把压缩的图片存储到指定文件
	     * @param bitmap  要被压缩图片的对象 bitmap (把要被压缩的图片转为bitmap对象)
	     * @param format 压缩的格式    JPEG    (0),PNG     (1),WEBP    (2);
	     * @param q      压缩质量      值是0-100，这里100表示原来图片的质量，不压缩
	     * @param path    把压缩后的图片的存放的路径目录
	     * @param maxSize   最大的图片大小
	     */    
     1.2 compress(CompressFormat format, int quality, OutputStream stream)
 

     1.3  封装的工具类保存Bitmap
          
        方法一:
         Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jett);
          String  path =   Environment.getExternalStorageDirectory()+"/test_q.jpeg";
 
        private void compress(Bitmap bitmap, Bitmap.CompressFormat format, int q, String path，long maxSize) {
            int byteCount = bitmap.getByteCount();
            Log.i("yc压缩图片","压缩前大小"+byteCount);    
            FileOutputStream fos = null;           
           try { 
           fos = new FileOutputStream(path);
            // 把ByteArrayInputStream数据生成图片
            // 质量压缩方法，options的值是0-100，这里100表示原来图片的质量，不压缩，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            }catch ( Exception e) {
            e.printStackTrace();
        } finally {
                
              if (null != fos){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
         }
 



        }


         方法二:

			public static String save(Bitmap  bitmap, Bitmap.CompressFormat format, int quality,File desFile) {
        try{
            FileOutputStream out = new FileOutputStream(desFile);
            if(bitmap.compress(format,quality,out)){
                out.flush();
                out.close();
            }
            if(bitmap!=null&&!bitmap.isRecycled()){
                bitmap.recycle();
            }
            return  desFile.getAbsolutePath();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





     方法三: 保存bitmap 图片 到sd卡

      public static String save(Bitmap  bitmap, Bitmap.CompressFormat format, int quality,Context context) {
        if(!Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        File dir = new File(Environment.getExternalStorageDirectory()+
        "/"+context.getPackageName());
        if(!dir.exists()){
            dir.mkdir();
        }
        File desFile = new File(dir, UUID.randomUUID().toString());
        return save(bitmap,format,quality,desFile);
      }


  
  




 
	2 采样率压缩(内存压缩)(BitmapFactory.Options.inSampleSize) 
			
         2.1 原理:  设置 BitmapFactory.Options.inSampleSize=n
            设置inSampleSize的值(int类型)后，假如设为n，则宽和高都为原来的1/n，宽高都减少，内存降低。
			 例如:一张宽高为2048x1536的图片，设置inSampleSize为4之后，实际加载到内存中的图片宽高是512x384。
				占有的内存就是0.75M而不是12M，足足节省了15倍。
          
          2.2  为什么叫采样率压缩?怎么体现的采样率?
              第一种采样大小压缩方法: 不使用BitmapFactory.Options.inJustDecodeBounds = true，
                                     直接按我给出的固定的宽高来取样压缩图片 ,直接设置BitmapFactory.Options.inSampleSize=n

              第二种采样率压缩方法: 设置BitmapFactory.Options.inJustDecodeBounds = true,来先获取图片的宽、高(这个过程就是取样)，可以在Bitmap不被加载到内存的前提下，获取Bitmap的原始宽高值
								  通过获取的宽高后,动态的设置BitmapFactory.Options.inSampleSize=n，设置inSampleSize之后，Bitmap的宽、高都会缩小inSampleSize倍,可以真实的压缩Bitmap占用的内存，加载更小内存的Bitmap。
        
    
              
         2.3 代码案例
              /**
				 * 第二种：按采样大小压缩
				 *
				 * @param src       源图片
				 * @param maxWidth  最大宽度
				 * @param maxHeight 最大高度
				 * @param recycle   是否回收
				 * @return 按采样率压缩后的图片
				 */
				public static Bitmap compressBySampleSize(final Bitmap src, final int maxWidth, final int maxHeight, final boolean recycle) {
				    if (src == null || src.getWidth() == 0 || src.getHeight() == 0) {
				        return null;
				    }
				    Log.i("yc压缩图片","压缩前大小"+src.getByteCount());
				    BitmapFactory.Options options = new BitmapFactory.Options();
				    options.inJustDecodeBounds = true;
				    ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    src.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				    byte[] bytes = baos.toByteArray();
				    BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
				    options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
				    options.inJustDecodeBounds = false;
				    if (recycle && !src.isRecycled()) {
				        src.recycle();
				    }
				    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
				    Log.i("yc压缩图片","压缩后大小"+bitmap.getByteCount());
				    return bitmap;
				}
				
				/**
				 * 计算获取缩放比例inSampleSize
				 */
				private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
				    final int height = options.outHeight;
				    final int width = options.outWidth;
				    int inSampleSize = 1;
				    if (height > reqHeight || width > reqWidth) {
				        final int heightRatio = Math.round((float) height / (float) reqHeight);
				        final int widthRatio = Math.round((float) width / (float) reqWidth);
				        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
				    }
				    final float totalPixels = width * height;
				    final float totalReqPixelsCap = reqWidth * reqHeight * 2;
				    while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				        inSampleSize++;
				    }
				    return inSampleSize;
				}
				 



3.Bitmap的缩放法压缩
            使用Matrix对图像进行缩放、旋转、平移、斜切等变换的。















五:Bitmap图片处理及转换

 
1.Bitmap，Drawable，InputStream，Byte[ ] 之间进行转换
Bitmap：decodeFile、decodeResource、decodeStream、decodeByteArray；


 		     
           	/**
			 * Drawable转化成Bitmap
			 * @param drawable                      drawable
			 * @return                              Bitmap
			 */
			public static Bitmap drawableToBitmap(Drawable drawable) {
			    int intrinsicWidth = drawable.getIntrinsicWidth();
			    int intrinsicHeight = drawable.getIntrinsicHeight();
			    Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE
			            ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
			    Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight,config);
			    Canvas canvas = new Canvas(bitmap);
			    drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
			    drawable.draw(canvas);
			    return bitmap;
			}



 			
             /**
			 * Bitmap转换成Drawable
			 * @param resources                     resources
			 * @param bm                            bm
			 * @return
			 */
			public static Drawable bitmapToDrawable(Resources resources, Bitmap bm) {
			    Drawable drawable = new BitmapDrawable(resources, bm);
			    return drawable;
			}




2.将View控件转换为bitmap
		public static Bitmap convertViewToBitMap(View view){
		    // 打开图像缓存
		    view.setDrawingCacheEnabled(true);
		    // 必须调用measure和layout方法才能成功保存可视组件的截图到png图像文件
		    // 测量View大小
		    int i = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		    int n = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		    view.measure(i, n);
		    // 发送位置和尺寸到View及其所有的子View
		    view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		    // 获得可视组件的截图
		    Bitmap bitmap = view.getDrawingCache();
		    return bitmap;
		}




 六:Bitmap优化如何避免OOM

     6.1 加载一个本地的大图片或者网络图片，从加载到设置到View上，如何减下内存，避免加载图片OOM？
       1.先将图片进行压缩
       2.压缩后的图片大小应该和用来展示它的控件view大小相近
    
       
     
     6.2 图像数据较大就会造成bitmap对象申请的内存较多



 七:实现加载图片步骤

  1.加载每张图片的时候都先检查一下图片的大小，保证这些图片都不会超出你程序的可用内存
  2.当知道图片的大小了，我们就可以决定是把整张图片加载到内存中还是加载一个压缩版的图片到内存中。









八:blog


https://blog.csdn.net/m0_37700275/article/details/84554654
https://github.com/yangchong211/YCBlogs/tree/master/android/多媒体

https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650243893&idx=1&sn=63fe28bbd9204b5f808708e14e57e1e8&chksm=8863725abf14fb4cf0cb60074e3224c3ea4852b44948d48f8ea0e45ccf21f0cf595459ec6b36&mpshare=1&scene=23&srcid=0904CkPwkyLwgjjdkcqq5AGN&sharer_sharetime=1573109323301&sharer_shareid=a3f31c715f1d693c100da0db4d25f197#rd










