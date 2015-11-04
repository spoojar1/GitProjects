var slideShowSpeed1 = 5000
var crossFadeDuration1 = 3
var Pic1 = new Array() 
Pic1[0] = 'images/cricket.jpg'
Pic1[1] = 'images/football.jpg'
Pic1[2] = 'images/mario.jpg'
Pic1[3] = 'images/canvas.png'
var t1
var j1 = 0
var p1 = Pic1.length
var preLoad1 = new Array()
for (i = 0; i < p1; i++)
{
   preLoad1[i] = new Image()
   preLoad1[i].src = Pic1[i]
}
function runSlideShow1()
{
   if (document.all)
   {
      document.images.SlideShow1.style.filter="blendTrans(duration=2)"
      document.images.SlideShow1.style.filter="blendTrans(duration=crossFadeDuration1)"
      document.images.SlideShow1.filters.blendTrans.Apply()      
   }
   document.images.SlideShow1.src = preLoad1[j1].src
   if (document.all)
   {
      document.images.SlideShow1.filters.blendTrans.Play()
   }
   j1 = j1 + 1
   if (j1 > (p1-1)) j1=0
   t1 = setTimeout('runSlideShow1()', slideShowSpeed1)
}