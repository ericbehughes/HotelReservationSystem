package dw317.lib.ListUtilitiesTest;

public class MergeTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		 @SuppressWarnings("rawtypes")
		Comparable[] list1 = {2, 3, 5, 7, 10, 11},
			      list2 = {5, 7, 8, 77, 88, 111},
			      list3 = new Comparable[12];
			int l1counter = 0;
			int l2counter = 0;
			
			for (int i =0; i < list1.length + list2.length-1; i++)
			{
				if (l1counter != list1.length && l2counter != list2.length)
				{
					if (list1[l1counter].compareTo(list2[l2counter]) <= 0){
						list3[i] = list1[l1counter];
						l1counter++;
					}
					else{
						list3[i] = list2[l2counter];
						l2counter++;
					}
				
				}
				else
				{
					if (l1counter == list1.length)
					{
						list3[i] = list2[l2counter];
						l2counter++;
					}
					else
					{
						list3[i] = list1[l1counter];
						l1counter++;
					}
				}
			}
				
	 			for (Comparable arr : list3)
	 				System.out.println(arr.toString());
	}

	}


