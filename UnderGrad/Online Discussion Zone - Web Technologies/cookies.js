
function getCookie(NameOfCookie)
{
	var end;
	if (document.cookie.length > 0)
	{
		begin = document.cookie.indexOf(NameOfCookie+"=");
		if (begin != -1) 
		{
			begin += NameOfCookie.length+1;
			end = document.cookie.indexOf(";", begin);
			if (end == -1) 
			end = document.cookie.length;
			return document.cookie.substring(begin,end)
		}
		else
			return null;
	}
	return null;
}
function setCookie(NameOfCookie, value, expiredays)
{
	var date = new Date ();
	date.setTime(date.getTime() + (expiredays * 24 * 3600 * 1000));
	var expires = "; expires="+date.toGMTString();

	document.cookie = NameOfCookie + "=" + escape(value) + expires+"; path=/";

}
function delCookie(NameOfCookie)
{
	var dt = new Date();
	if (getCookie(NameOfCookie))
	{
		setCookie(NameOfCookie,'',-1);
	}	
}
