var slideShowSpeed4 = 7000
var crossFadeDuration4 = 3
var Pic4 = new Array() 
Pic4[0] = 'images/index.jpg'
Pic4[1] = 'images/book.jpg'
Pic4[2] = 'images/secuengg.jpg'
Pic4[3] = 'images/weimg.jpg'
var t4
var j4 = 0
var p4 = Pic4.length
var preLoad4 = new Array()
for (i = 0; i < p4; i++)
{
   preLoad4[i] = new Image()
   preLoad4[i].src = Pic4[i]
}
function runSlideShow4()
{
   if (document.all)
   {
      document.images.SlideShow4.style.filter="blendTrans(duration=2)"
      document.images.SlideShow4.style.filter="blendTrans(duration=crossFadeDuration4)"
      document.images.SlideShow4.filters.blendTrans.Apply()      
   }
   document.images.SlideShow4.src = preLoad4[j4].src
   if (document.all)
   {
      document.images.SlideShow4.filters.blendTrans.Play()
   }
   j4 = j4 + 1
   if (j4 > (p4-1)) j4=0
   t4 = setTimeout('runSlideShow4()', slideShowSpeed4)
}