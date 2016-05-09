var slideShowSpeed3 = 5000
var crossFadeDuration3 = 3
var Pic3 = new Array() 
Pic3[0] = 'images/ronclark.jpg'
Pic3[1] = 'images/tux.jpg'
Pic3[2] = 'images/loveactually.jpg'
var t3
var j3 = 0
var p3 = Pic3.length
var preLoad3 = new Array()
for (i = 0; i < p3; i++)
{
   preLoad3[i] = new Image()
   preLoad3[i].src = Pic3[i]
}
function runSlideShow3()
{
   if (document.all)
   {
      document.images.SlideShow3.style.filter="blendTrans(duration=2)"
      document.images.SlideShow3.style.filter="blendTrans(duration=crossFadeDuration3)"
      document.images.SlideShow3.filters.blendTrans.Apply()      
   }
   document.images.SlideShow3.src = preLoad3[j3].src
   if (document.all)
   {
      document.images.SlideShow3.filters.blendTrans.Play()
   }
   j3 = j3 + 1
   if (j3 > (p3-1)) j3=0
   t3 = setTimeout('runSlideShow3()', slideShowSpeed3)
}