import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Runner
{
	private static List<Integer> mergeSortedListsByStream(List<List<Integer>> lists)
	{
		return lists.stream()
				.flatMap(integers -> integers.stream())
				.sorted()
				.collect(Collectors.toList());
	}

	private static List<Integer> mergeSortedListsByLoopsAndCollectionsSort(List<List<Integer>> lists)
	{
		int totalSize = 0;
		for (List<Integer> list : lists) {
			totalSize += list.size();
		}
		List<Integer> result = new ArrayList<>(totalSize);

		for (List<Integer> list : lists) {
			for (Integer i: list) {
				result.add(i);
			}
		}

		Collections.sort(result);
		return result;
	}

	private static List<Integer> mergeSortedListsByComparingAndRemoving(List<List<Integer>> lists) {
		int totalSize = 0;
		for (List<Integer> list : lists) {
			totalSize += list.size();
		}

		List<Integer> result = new ArrayList<>(totalSize);

		List<Integer> lowest;

		while (result.size() < totalSize) {
			lowest = null;

			for (List<Integer> list : lists) {
				if (!list.isEmpty()) {
					if (lowest == null) {
						lowest = list;
					} else if (list.get(0).compareTo(lowest.get(0)) <= 0) {
						lowest = list;
					}
				}
			}

			result.add(lowest.get(0));
			lowest.remove(0);
		}

		return result;
	}
	
	private static List<List<Integer>> getCommonList()
	{
		List<List<Integer>> commonList = new ArrayList<>();
		for (int i = 1; i < 10; i++)
		{
			List<Integer> list = new ArrayList<>();
			for (int j = 1; j < 1000; j = j+i)
			{
				list.add(j);
			}
			
			commonList.add(list);
		}
		
		return commonList;
	}

	public static void main(String[] args)
	{
		List<List<Integer>> commonList;
		long begin;
		List<Integer> integers;

		commonList = getCommonList();
		begin = System.nanoTime();
		integers = mergeSortedListsByStream(commonList);
		System.out.println("Streams initial.Execution time = " + (System.nanoTime() - begin)/1000);
		integers.forEach(i -> System.out.print(i + " "));

		System.out.println();

		commonList = getCommonList();
		begin = System.nanoTime();
		integers = mergeSortedListsByStream(commonList);
		System.out.println("Streams warm.Execution time = " + (System.nanoTime() - begin)/1000);
		integers.forEach(i -> System.out.print(i + " "));

		System.out.println();

		commonList = getCommonList();
		begin = System.nanoTime();
		integers = mergeSortedListsByLoopsAndCollectionsSort(commonList);
		System.out.println("CollectionsSort.Execution time = " + (System.nanoTime() - begin)/1000);
		integers.forEach(i -> System.out.print(i + " "));

		System.out.println();

		commonList = getCommonList();
		begin = System.nanoTime();
		integers = mergeSortedListsByComparingAndRemoving(commonList);
		System.out.println("ComparingAndRemoving.Execution time = " + (System.nanoTime() - begin)/1000);
		integers.forEach(i -> System.out.print(i + " "));
	}
}