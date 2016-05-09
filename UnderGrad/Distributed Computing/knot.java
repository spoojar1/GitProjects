knot()
{
	int plist[]=new int[p];
	for(int i=1;i<=p;i++)
	{
		if(rlist[i]==1)
		{
			for(int j=1;j<=r;j++)
			{
				if(m[j][i]==1 || m[j][i]==2)
				plist[j]=1;
			}
		}
	}

	int ptest[][]=new int [p+1][p+1];
	for(int i=1;i<p;i++)
	{
		ptest[i][i]=1;
	}

	for(int i=1;i<=p;i++)
	{
		if(plist[i]==1)
		{
			check(i);
		}
	}

	for(i=1;i<=p;i++)
	{
		for(j=1;j<=p;j++)
		{
			if(ptest[i][j]==1)
			for(k=1;k<=p;k++)
			{
				if(ptest[j][k]==1)
				ptest[i][k]=1;
			}
		}
	}

}

check(int ch)
{
	for(i=1;i<=r;i++)
	{
		if(m[ch][i]==2)
		{
			for(int j=1;j<=p;j++)
			{
				if(m[j][i]==1)
				{
					ptest[m][j]=1;
				}
			}
		}
	}
}

