package amery.hash;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashConflictTester {

    /**
     * 以1-256为乘数，分别计算/usr/share/dict/words所有单词的哈希冲突率、总耗时.
     *
     * @throws IOException
     */
    @Test
    public void testHash() throws IOException {
        List<String> words = getWords();

        System.out.println();
        System.out.println("multiplier, conflictSize, conflictRate, timeCost, listSize, minHash, maxHash");
        for (int i = 1; i <= 256; i++) {
            computeConflictRate(words, i);
        }
    }

    /**
     * 读取/usr/share/dict/words所有单词
     *
     * @return
     * @throws IOException
     */
    private List<String> getWords() throws IOException {
        // read file
        InputStream is = HashConflictTester.class.getClassLoader().getResourceAsStream("web2.txt");
        List<String> lines = IOUtils.readLines(is, "UTF-8");
        return lines;
    }

    /**
     * 计算冲突率
     *
     * @param lines
     */
    private void computeConflictRate(List<String> lines, int multiplier) {
        // compute hash
        long startTime = System.currentTimeMillis();
        List<Integer> hashList = computeHashes(lines, multiplier);
        long timeCost = System.currentTimeMillis() - startTime;

        // find max and min hash
        Comparator<Integer> comparator = (x, y) -> x > y ? 1 : (x < y ? -1 : 0);
        int maxHash = hashList.parallelStream().max(comparator).get();
        int minHash = hashList.parallelStream().min(comparator).get();

        // hash set
        Set<Integer> hashSet = hashList.parallelStream().collect(Collectors.toSet());

        int conflictSize = lines.size() - hashSet.size();
        float conflictRate = conflictSize * 1.0f / lines.size();
        System.out.println(String.format("%s, %s, %s, %s, %s, %s, %s", multiplier, conflictSize, conflictRate, timeCost, lines.size(), minHash, maxHash));
    }

    /**
     * 根据乘数计算hash值
     *
     * @param lines
     * @param multiplier
     * @return
     */
    private List<Integer> computeHashes(List<String> lines, int multiplier) {
        Function<String, Integer> hashFunction = x -> {
            int hash = 0;
            for (int i = 0; i < x.length(); i++) {
                hash = (multiplier * hash) + x.charAt(i);
            }
            return hash;
        };
        return lines.parallelStream().map(hashFunction).collect(Collectors.toList());
    }
}
