package chap2;

public class TopDownMergeSortFastInsertion<T extends Comparable<T>> implements Sortable<T>
{
	private Comparable<T>[] aux;
	
	private void sort(Comparable<T>[] a, int lo, int hi)
	{
//		if(lo >= hi)
//		{
//			return;
//		}
		
		// If the size is smaller than threshold, use insertion sort
		if(lo + 15 >= hi)
		{
			InsertionSort.sort(a, lo, hi);
			return;
		}
		
		int mid = lo + (hi - lo) / 2;
		
		// Recursive in a top-down style
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
//		SortUtils.show(a);
	}
	
	// Merge sort enhancement 
	private void merge(Comparable<T>[] a, int lo, int mid, int hi)
	{
//		System.out.println("Merge (" + lo + ", " + mid + ", " + hi + ")");
		
		// Copy a[lo..hi] to aux[lo..hi].
		// First half copy
		for (int k = lo; k <= mid; k++) 
		{
			aux[k] = a[k];
		}
		
		// Second half copy - in reverse order
		for(int k = mid + 1, m = hi; k <= hi; k++, m--)
		{
			aux[k] = a[m];
		}
		
		// Two sentinel index
		int i = lo;
		int j = hi;
		
		for(int k = lo; k <= hi; k++)
		{
			// Two indexes cross
//			if(i == j)
//			{
//				// Set the last element, cross index means it
//				a[k] = aux[i];
//				return;
//			}
			
			// Left element is less than the right one
			if(SortUtils.lessOrEqual(aux[i], aux[j]))
			{
				a[k] = aux[i++];
			}
			// Right element is less than the left one
			else
			{
				a[k] = aux[j--];
			}
		}
	}

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		this.init(a);
		
		this.sort(a, 0, a.length - 1);
	}

	// Do init work - init aux, and to dump elements into it from a
	@SuppressWarnings("unchecked")
	private void init(Comparable<T>[] a)
	{
		this.aux = new Comparable[a.length];
		
		for(int i = 0; i < a.length; i++)
		{
			aux[i] = a[i];
		}
	}
}
