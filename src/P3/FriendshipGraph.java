package P3;

import java.util.*;

public class FriendshipGraph {
	private LinkedList<Person> vertex = new LinkedList<Person>();
	private int temp = vertex.size();
	private int[][] a,b;
	public void addVertex (Person people) throws RuntimeException
	{
		for(Person p : vertex)
		{
			if(p.get().equals(people.get()))
			{
				throw new RuntimeException();
			}

		}
		vertex.add(people);
		
	}
	public void begin()     //begin()函数用来初始化邻接矩阵
	{
		temp = vertex.size();
		 a = new int[temp][temp];
		 b = new int[temp][temp];
		for(int i=0;i<temp;i++) {
			for(int j=0;j<temp;j++)
			{
				if(i!=j)
				{
					a[i][j] = 999;
					b[i][j] = 999;
				}
				else
				{
				   a[i][j] = 0;
				   b[i][j] = 0;
				}

			}
		}
	}	
	public void addEdge(Person p1,Person p2) {
		int i=vertex.indexOf(p1);
		int j = vertex.indexOf(p2);
        a[i][j] = 1;
        b[i][j] = 1;
	}
	public void Floyd()      //Floyd()函数用来得到表示任意两个点之间最短距离的矩阵
	{
		temp = vertex.size();
		for(int k=0;k<temp;k++)
		{
			for(int i=0;i<temp;i++)
			{
				for(int j=0;j<temp;j++)
				{
					if(b[i][k]+b[k][j]<b[i][j])
					{
						if(b[i][k]+b[k][j]!=0)
							b[i][j] = b[i][k] + b[k][j];
						
					}
				}
			}
		}
	}
	public int getDistance(Person p1,Person p2) {
		int i=vertex.indexOf(p1);
		int j = vertex.indexOf(p2);
		if(b[i][j]==999)
		{
			return -1;
		}
		return b[i][j];
	}
	public static void main(String args[])
	{
		FriendshipGraph friend = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross"); 
		Person ben = new Person("Ben"); 
		Person kramer = new Person("Kramer");
		friend.addVertex(rachel);
		friend.addVertex(ross);
		friend.addVertex(ben);
		friend.addVertex(kramer);
		friend.begin();
		friend.addEdge(rachel, ross);
		friend.addEdge(ross, rachel);
		friend.addEdge(ross, ben); 
		friend.addEdge(ben, ross);
		friend.Floyd();
		System.out.println(friend.getDistance(rachel, ross));
		System.out.println(friend.getDistance(rachel, ben));
		System.out.println(friend.getDistance(rachel, rachel));
		System.out.println(friend.getDistance(rachel, kramer));


	}
	

}
