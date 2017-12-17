import java.util.*;
import java.util.stream.Collectors;

public class Runner
{
    private static List<Integer> mergeSortedListsByStream(List<List<Integer>> lists)
    {
        return lists.stream()
                .flatMap(Collection::stream)
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

    public static void main(String[] args)
    {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        for (int i = 1; i < 50; i = i + 2) {
            list1.add(i);
        }
        for (int i = 1; i < 50; i = i + 3) {
            list2.add(i);
        }
        for (int i = 1; i < 50; i = i + 4) {
            list3.add(i);
        }

        List<List<Integer>> commonList = Arrays.asList(list1, list2, list3);

        long begin = System.nanoTime();
        List<Integer> integers = mergeSortedListsByStream(commonList);
        System.out.println("Streams.Execution time = " + (System.nanoTime() - begin)/1000);
        integers.forEach(i -> System.out.print(i + " "));

        System.out.println();

        begin = System.nanoTime();
        List<Integer> integers2 = mergeSortedListsByLoopsAndCollectionsSort(commonList);
        System.out.println("CollectionsSort.Execution time = " + (System.nanoTime() - begin)/1000);
        integers2.forEach(i -> System.out.print(i + " "));

        System.out.println();

        begin = System.nanoTime();
        List<Integer> integers3 = mergeSortedListsByComparingAndRemoving(commonList);
        System.out.println("ComparingAndRemoving.Execution time = " + (System.nanoTime() - begin)/1000);
        integers3.forEach(i -> System.out.print(i + " "));
    }
}
