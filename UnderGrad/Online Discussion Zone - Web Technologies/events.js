var slideShowSpeed5 = 7000
var crossFadeDuration5 = 3
var Pic5 = new Array() 
Pic5[0] = 'images/moodi.jpg'
Pic5[1] = 'images/roboveda.jpg'
Pic5[2] = 'images/cressida.jpg'
Pic5[3] = 'images/isaac.jpg'
var t5
var j5 = 0
var p5 = Pic5.length
var preLoad5 = new Array()
for (i = 0; i < p5; i++)
{
   preLoad5[i] = new Image()
   preLoad5[i].src = Pic5[i]
}
function runSlideShow5()
{
   if (document.all)
   {
      document.images.SlideShow5.style.filter="blendTrans(duration=2)"
      document.images.SlideShow5.style.filter="blendTrans(duration=crossFadeDuration5)"
      document.images.SlideShow5.filters.blendTrans.Apply()      
   }
   document.images.SlideShow5.src = preLoad5[j5].src
   if (document.all)
   {
      document.images.SlideShow5.filters.blendTrans.Play()
   }
   j5 = j5 + 1
   if (j5 > (p5-1)) j5=0
   t5 = setTimeout('runSlideShow5()', slideShowSpeed5)
}