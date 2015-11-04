var slideShowSpeed2 = 5000
var crossFadeDuration2 = 3
var Pic2 = new Array() 
Pic2[0] = 'images/friends.jpg'
Pic2[1] = 'images/castle.jpg'
Pic2[2] = 'images/heroes.jpg'
Pic2[3] = 'images/2.5mens.jpg'
var t2
var j2 = 0
var p2 = Pic2.length
var preLoad2 = new Array()
for (i = 0; i < p2; i++)
{
   preLoad2[i] = new Image()
   preLoad2[i].src = Pic2[i]
}
function runSlideShow2()
{
   if (document.all)
   {
      document.images.SlideShow2.style.filter="blendTrans(duration=2)"
      document.images.SlideShow2.style.filter="blendTrans(duration=crossFadeDuration2)"
      document.images.SlideShow2.filters.blendTrans.Apply()      
   }
   document.images.SlideShow2.src = preLoad2[j2].src
   if (document.all)
   {
      document.images.SlideShow2.filters.blendTrans.Play()
   }
   j2 = j2 + 1
   if (j2 > (p2-1)) j2=0
   t2 = setTimeout('runSlideShow2()', slideShowSpeed2)
}