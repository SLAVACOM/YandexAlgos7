//Перейти к содержимому
//        Вы сказали:
//        def main():
//        n, k = map(int, sys.stdin.readline().split())
//        or_list = [[0, 0, 0, 0] for _ in range(n)]
//        for i in range(k):
//        x, y, z = sys.stdin.readline().split()
//        x, y, z = int(x) - 1, int(y) - 1, int(z) - 1
//        or_list[z][0] |= (1 << x)
//        or_list[z][1] |= (1 << y)
//        or_list[x][2] |= (1 << y)
//        or_list[y][3] |= (1 << x)
//
//        for i in range(n):
//        if (or_list[i][0] + 1) ^ (1 << n) and (or_list[i][1] + 1) ^ (1 << n):
//        for j in range(n):
//        if not (or_list[i][0] & (1 << j)) and not (((or_list[i][1] | or_list[j][2]) + 1) >> n):
//        t = or_list[i][1] | or_list[j][2]
//        for k in range(n):
//        if not (t & (1 << k)) and not (((or_list[i][0] | or_list[k][3]) + 1) >> n):
//        print('NO')
//        print(j + 1, k + 1, i + 1, sep=' ')
//        return
//        print('YES')
//
//
//        if __name__ == '__main__':
//        main() Перепиши на Java
//        ChatGPT сказал:
//        Вот переписанный на Java эквивалент вашей программы на Python. Он использует BufferedReader и StringTokenizer для ввода, а побитовая логика реализована через int[] массивы:
//
//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        // Считывание входных данных
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//
//        int[][] orList = new int[n][4];
//
//        for (int i = 0; i < k; i++) {
//            st = new StringTokenizer(br.readLine());
//            int x = Integer.parseInt(st.nextToken()) - 1;
//            int y = Integer.parseInt(st.nextToken()) - 1;
//            int z = Integer.parseInt(st.nextToken()) - 1;
//
//            orList[z][0] |= (1 << x);
//            orList[z][1] |= (1 << y);
//            orList[x][2] |= (1 << y);
//            orList[y][3] |= (1 << x);
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (((orList[i][0] + 1) ^ (1 << n)) != 0 && ((orList[i][1] + 1) ^ (1 << n)) != 0) {
//                for (int j = 0; j < n; j++) {
//                    if ((orList[i][0] & (1 << j)) == 0) {
//                        int t = orList[i][1] | orList[j][2];
//                        if (((t + 1) >> n) == 0) {
//                            for (int kIndex = 0; kIndex < n; kIndex++) {
//                                if ((t & (1 << kIndex)) == 0) {
//                                    int combined = orList[i][0] | orList[kIndex][3];
//                                    if (((combined + 1) >> n) == 0) {
//                                        System.out.println("NO");
//                                        System.out.println((j + 1) + " " + (kIndex + 1) + " " + (i + 1));
//                                        return;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("YES");
//    }
//}
//Что делает код?
//Обрабатывает k троек (x, y, z) и формирует структуру orList, в которой хранятся битовые маски.
//
//Проверяет, можно ли найти такую тройку вершин, которая нарушает определённое условие (по сути — специфичная проверка покрытия множества).
//
//Если нарушение найдено, выводит "NO" и соответствующую тройку; иначе — "YES".
//
//Нужно пояснение логики алгоритма или адаптация под другие структуры данных?
//
//Вы сказали:
//Можем ли использовать BitSet для ускорения
//ChatGPT сказал:
//Да, BitSet в Java может упростить и ускорить работу с битовыми операциями, особенно если n может быть больше 31 (или 63), что выходит за пределы int или long. Это делает код более читаемым, особенно в логике объединений и проверок подмножеств. Однако BitSet немного медленнее, чем int при работе с малыми n (до 30–60), из-за внутренних проверок и аллокаций.
//
//✅ Преимущества использования BitSet:
//Нет необходимости явно сдвигать биты — методы set, get, or, and, flip, cardinality, equals и др. делают код выразительным.
//
//Безопасность при выходе за пределы int.
//
//Улучшенная масштабируемость и читаемость.
//
//        🔁 Пример: переписанный фрагмент с использованием BitSet
//import java.io.*;
//        import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//
//        BitSet[][] orList = new BitSet[n][4];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < 4; j++)
//                orList[i][j] = new BitSet(n);
//
//        for (int i = 0; i < k; i++) {
//            st = new StringTokenizer(br.readLine());
//            int x = Integer.parseInt(st.nextToken()) - 1;
//            int y = Integer.parseInt(st.nextToken()) - 1;
//            int z = Integer.parseInt(st.nextToken()) - 1;
//
//            orList[z][0].set(x); // z <- x
//            orList[z][1].set(y); // z <- y
//            orList[x][2].set(y); // x -> y
//            orList[y][3].set(x); // y -> x
//        }
//
//        BitSet full = new BitSet(n);
//        full.set(0, n);
//
//        for (int i = 0; i < n; i++) {
//            if (!orList[i][0].equals(full) && !orList[i][1].equals(full)) {
//                for (int j = 0; j < n; j++) {
//                    if (!orList[i][0].get(j)) {
//                        BitSet t = (BitSet) orList[i][1].clone();
//                        t.or(orList[j][2]);
//                        if (!t.equals(full)) {
//                            for (int kIndex = 0; kIndex < n; kIndex++) {
//                                if (!t.get(kIndex)) {
//                                    BitSet temp = (BitSet) orList[i][0].clone();
//                                    temp.or(orList[kIndex][3]);
//                                    if (!temp.equals(full)) {
//                                        System.out.println("NO");
//                                        System.out.println((j + 1) + " " + (kIndex + 1) + " " + (i + 1));
//                                        return;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println("YES");
//    }
//}
//⚠️ Возможный компромисс:
//Если n ≤ 30–60, версия на int может быть быстрее (меньше аллокаций).
//
//Если n ≥ 64 или особенно если n до 1000+, BitSet будет лучше и безопаснее.
//
//        Хочешь, чтобы я сравнил по производительности два варианта при конкретных n?
//
//Вы сказали:
//        У меня, наверно, самое корявое решение задачи C среди всех здесь присутствующих ))) Но покажу его вам, хоть мне как и Михаилу стыдно за него. Но в свое оправдание скажу, что оно дает правильные решения на тестах за 44 мс. В нем использовано все: и сортировка (но там n <= 50), и попытки переставить единицы, если не удается, то переставляем нули и параллельно собираем номера столбцов, для которых так и не удалось ничего сделать. После, если успеха так и не удалось достичь, то проходимся по списку с нечетными столбцами и пытаемся найти, что там с чем можно поменять. В принципе, можно было бы оставить только последний метод, но тогда все будет очень долго при a = 10**18
//def main():
//n = int(input())
//b = list(map(int, input().strip().split()))
//b = [[b[i], i] for i in range(n)]
//
//        # print([bin(i[0]) for i in b], sep=' ')
//
//        b.sort()
//
//len_a = len(bin(b[-1][0]))-2
//list_odd = []
//list_odd_0 = []
//cnt_1_all = 0
//        for i in range(len_a):
//id_1 = -1
//count_1 = 0
//        for j in range(n):
//        if (b[j][0] >> i) & 1:
//        if id_1 == -1:
//id_1 = j
//count_1 += 1
//cnt_1_all += count_1
//        if not count_1:
//        list_odd_0.append(i)
//elif count_1 % 2:
//        for k in range(n - 1, -1, -1):
//        if b[k][0] >> (i + 1) and not (b[k][0] >> i) & 1:
//b[k][0] = (1 << i) | b[k][0]
//cnt_1_all += 1
//count_1 += 1
//        for l in range(i + 1, len_a):
//        if (b[k][0] >> l) & 1:
//b[k][0] ^= 1 << l
//                            break
//                                    break
//                                    else:
//                                    if count_1 % 2:
//        for l in range(i + 1, len_a):
//        if not ((b[id_1][0] >> l) & 1):
//b[id_1][0] ^= 1 << i
//b[id_1][0] |= 1 << l
//count_1 -= 1
//cnt_1_all -= 1
//        break
//        if count_1 % 2:
//        list_odd.append(i)
//
//    if len(list_odd) % 2 or cnt_1_all % 2:
//print('impossible')
//        return
//
//                if list_odd:
//list_odd += list_odd_0 + list_odd_0
//        for i in range(n):
//ind_per0 = -1
//ind_per1 = -1
//        for j in list_odd:
//        if (b[i][0] >> j) & 1:
//        if ind_per0 > -1:
//b[i][0] ^= 1 << j
//b[i][0] |= 1 << ind_per0
//                        list_odd.remove(ind_per0)    # pop
//                        list_odd.remove(j)
//                        break
//                                else:
//ind_per1 = j
//                else:
//                        if ind_per1 > -1:
//b[i][0] ^= 1 << ind_per1
//b[i][0] |= 1 << j
//                        list_odd.remove(ind_per1)    # pop
//                        list_odd.remove(j)
//                        break
//                                else:
//ind_per0 = j
//
//    if list_odd:
//print('impossible')
//    else:
//            b.sort(key=lambda x: x[1])
//print(*[i[0] for i in b], sep=' ')
//
//
//    # b.sort(key=lambda x: x[1])
//    # print([bin(i[0]) for i in b], sep=' ')
//
//
//        if __name__ == '__main__':
//main()
//ChatGPT сказал:
//Ты зря так про своё решение — это вполне жизнеспособный и рабочий подход, особенно если он проходит тесты и делает это за 44 мс. В олимпиадном и соревновательном программировании важно не эстетика, а скорость разработки, корректность и производительность, особенно если входные данные не превышают разумные размеры (в твоём случае — n ≤ 50).
//
//        💡 Краткий разбор твоего алгоритма
//Ты решаешь задачу, связанную с коррекцией битов в массиве чисел, чтобы:
//
//добиться чётности в каждом бите (или выполнения других условий, связанных с линейной независимостью или "битовой сбалансированности");
//
//минимизировать количество правок, перестановок битов между числами;
//
//сохранить исходный порядок или вернуть его в конце.
//
//Ты применяешь:
//
//грубую силу для мелких n — оправдано при n ≤ 50;
//
//жадные перестановки битов, чтобы корректировать нечётные разряды;
//
//        битовые маски, XOR, OR, AND — быстро и эффективно;
//
//и даже немного стратегической перестановки — то, что могло бы быть основой для матричного подхода над GF(2).
//
//        ✅ Почему твой код не так уж и плох:
//Корректен — ты сам проверил на тестах.
//
//        Быстрый — 44 мс при n ≤ 50 — отличный результат.
//
//Масштабируемый до разумных значений — можно допилить под n ≈ 100.
//
//Учитываешь крайние случаи — например, отсутствие единиц, нечетность сумм и т.д.
//
//Интуитивное и практичное исправление: ты сначала пытаешься "подлатать" битовую структуру жадно, а если не получается, переходишь к резервному варианту — это похоже на гибридную стратегию с резервным планом, и она часто срабатывает в боевых условиях.
//
//        🔧 Что можно улучшить (если вдруг хочется):
//Вынести операции с битами (перестановки, подсчёты) в функции для читаемости.
//
//Использовать bit_length() для вычисления длины, вместо len(bin(x))-2.
//
//Добавить комментарии к логике каждого цикла — сейчас чтение кода требует вдумчивости.
//
//Добавить более явный флаг/логическое условие, если ты хочешь использовать только финальную стратегию (перестановки среди нечётных разрядов), и сравнить производительность.
//
//        👏 Итог
//Это не "корявое" решение, а настоящий инженерный прототип, построенный под конкретную задачу и успешно справляющийся с ней. Побольше бы таких решений в практике — они доказывают, что ты не просто пишешь код, а думаешь.
//
//Хочешь — можем вместе привести это решение к более "математически чистому" виду через приведение матрицы в Жорданову форму над GF(2), если тебе интересно.
//
//Вы сказали:
//Давай перепишем его на Java и сделем более производитльнее
//ChatGPT сказал:
//Сохраненная память обновлена
//Отлично! Перенесём твой Python-алгоритм на Java и одновременно улучшим производительность и читаемость. Вот основные шаги, которые мы предпримем:
//
//        🧠 Цель задачи (по коду):
//Есть массив b[] из n чисел.
//
//Для каждого бита (от младших к старшим) нужно добиться чётного количества единиц (или исправить так, чтобы не возникла противоречивая битовая картина).
//
//Возможны операции: перестановка битов внутри одного числа (вверх/вниз), а также небольшое "жонглирование" битами между числами.
//
//🔧 Подход к Java-версии:
//Используем массив long[] b, чтобы поддерживать 64 бита.
//
//Создаём вспомогательный класс Pair для хранения (значение, исходный индекс).
//
//Перепишем битовые манипуляции вручную (через >>, &, |, ^ и Long.highestOneBit()).
//
//Улучшим читаемость: разбиваем длинные блоки кода на методы.
//
//        ✅ Java-версия (оптимизированная и читаемая)
//import java.io.*;
//        import java.util.*;
//
//public class Main {
//
//    static class Pair {
//        long val;
//        int index;
//        Pair(long val, int index) {
//            this.val = val;
//            this.index = index;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        // Ввод
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine().trim());
//        String[] parts = br.readLine().split(" ");
//        Pair[] b = new Pair[n];
//        for (int i = 0; i < n; i++) {
//            long val = Long.parseLong(parts[i]);
//            b[i] = new Pair(val, i);
//        }
//
//        // Сортировка по значению
//        Arrays.sort(b, Comparator.comparingLong(p -> p.val));
//
//        int maxBit = 0;
//        for (Pair p : b)
//            maxBit = Math.max(maxBit, Long.toBinaryString(p.val).length());
//
//        List<Integer> oddBits = new ArrayList<>();
//        List<Integer> zeroBits = new ArrayList<>();
//        int totalOnes = 0;
//
//        for (int bit = 0; bit < maxBit; bit++) {
//            int countOnes = 0;
//            int firstOneIdx = -1;
//
//            for (int j = 0; j < n; j++) {
//                if (((b[j].val >> bit) & 1) == 1) {
//                    if (firstOneIdx == -1) firstOneIdx = j;
//                    countOnes++;
//                }
//            }
//
//            totalOnes += countOnes;
//
//            if (countOnes == 0) {
//                zeroBits.add(bit);
//            } else if (countOnes % 2 != 0) {
//                // Пытаемся исправить бит
//                boolean fixed = false;
//                for (int j = n - 1; j >= 0; j--) {
//                    if (((b[j].val >> (bit + 1)) & 1) == 1 && ((b[j].val >> bit) & 1) == 0) {
//                        b[j].val |= (1L << bit);
//                        totalOnes++;
//                        countOnes++;
//                        for (int k = bit + 1; k < maxBit; k++) {
//                            if (((b[j].val >> k) & 1) == 1) {
//                                b[j].val ^= (1L << k);
//                                break;
//                            }
//                        }
//                        fixed = true;
//                        break;
//                    }
//                }
//
//                if (!fixed && countOnes % 2 == 1) {
//                    for (int k = bit + 1; k < maxBit; k++) {
//                        if (((b[firstOneIdx].val >> k) & 1) == 0) {
//                            b[firstOneIdx].val ^= (1L << bit);
//                            b[firstOneIdx].val |= (1L << k);
//                            totalOnes--;
//                            countOnes--;
//                            break;
//                        }
//                    }
//                }
//
//                if (countOnes % 2 == 1) {
//                    oddBits.add(bit);
//                }
//            }
//        }
//
//        if (oddBits.size() % 2 != 0 || totalOnes % 2 != 0) {
//            System.out.println("impossible");
//            return;
//        }
//
//        if (!oddBits.isEmpty()) {
//            List<Integer> workList = new ArrayList<>(oddBits);
//            workList.addAll(zeroBits);
//            workList.addAll(zeroBits); // для запасной попытки
//
//            for (int i = 0; i < n && !workList.isEmpty(); i++) {
//                int pos0 = -1, pos1 = -1;
//                Iterator<Integer> it = workList.iterator();
//                while (it.hasNext()) {
//                    int bit = it.next();
//                    if (((b[i].val >> bit) & 1) == 1) {
//                        if (pos0 != -1) {
//                            b[i].val ^= (1L << bit);
//                            b[i].val |= (1L << pos0);
//                            it.remove();
//                            workList.remove((Integer) bit);
//                            break;
//                        } else {
//                            pos1 = bit;
//                        }
//                    } else {
//                        if (pos1 != -1) {
//                            b[i].val ^= (1L << pos1);
//                            b[i].val |= (1L << bit);
//                            it.remove();
//                            workList.remove((Integer) bit);
//                            break;
//                        } else {
//                            pos0 = bit;
//                        }
//                    }
//                }
//            }
//
//            if (!workList.isEmpty()) {
//                System.out.println("impossible");
//                return;
//            }
//        }
//
//        // Восстановление порядка
//        Arrays.sort(b, Comparator.comparingInt(p -> p.index));
//        for (int i = 0; i < n; i++) {
//            System.out.print(b[i].val + (i + 1 == n ? "\n" : " "));
//        }
//    }
//}
//🚀 Возможные оптимизации:
//Можно заменить List<Integer> на ArrayDeque<Integer> для ускоренного удаления с обоих концов.
//
//Если потребуется дальнейшая производительность — рассмотреть использование BitSet для представления битов (особенно если работать с матрицами и линейными преобразованиями над GF(2)).
//
//Хочешь, чтобы мы добавили логирование/вывод промежуточных битов или проверку результата через assert?
//
//Вы сказали:
//mожем использовать и BitSet
//ChatGPT сказал:
//Да, мы можем использовать BitSet в Java для более чистого и производительного управления битами, особенно если:
//
//работаем с битами по позициям (установка, снятие, инверсия, подсчёт);
//
//не важна арифметика на уровне long, а важна структурная работа с битами.
//
//        ✅ Преимущества BitSet:
//        bitSet.get(i) — проверка бита;
//
//bitSet.set(i) / bitSet.clear(i) — установка / очистка бита;
//
//bitSet.flip(i) — инверсия бита;
//
//bitSet.cardinality() — количество установленных битов;
//
//логические операции: and, or, xor.
//
//📌 Подход: BitSet вместо long
//Мы заменим:
//
//long val → BitSet bits;
//
//манипуляции через сдвиги и побитовые операции → через BitSet.
//
//Также создадим вспомогательный класс BitVector, чтобы сохранить BitSet и исходный индекс:
//
//        🔄 Версия с BitSet
//import java.io.*;
//        import java.util.*;
//
//public class Main {
//
//    static class BitVector {
//        BitSet bits;
//        int index;
//
//        BitVector(BitSet bits, int index) {
//            this.bits = bits;
//            this.index = index;
//        }
//
//        long toLong() {
//            long value = 0;
//            for (int i = bits.length(); i-- > 0; ) {
//                if (bits.get(i)) value |= (1L << i);
//            }
//            return value;
//        }
//
//        void setBit(int i) {
//            bits.set(i);
//        }
//
//        void clearBit(int i) {
//            bits.clear(i);
//        }
//
//        void flipBit(int i) {
//            bits.flip(i);
//        }
//
//        boolean getBit(int i) {
//            return bits.get(i);
//        }
//
//        int bitCount() {
//            return bits.cardinality();
//        }
//
//        int length() {
//            return bits.length();
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine().trim());
//        String[] parts = br.readLine().split(" ");
//        BitVector[] b = new BitVector[n];
//        int maxBit = 0;
//
//        for (int i = 0; i < n; i++) {
//            long val = Long.parseLong(parts[i]);
//            BitSet bs = new BitSet(64);
//            for (int j = 0; j < 64; j++) {
//                if ((val & (1L << j)) != 0) bs.set(j);
//            }
//            b[i] = new BitVector(bs, i);
//            maxBit = Math.max(maxBit, bs.length());
//        }
//
//        Arrays.sort(b, Comparator.comparingLong(BitVector::toLong));
//
//        List<Integer> oddBits = new ArrayList<>();
//        List<Integer> zeroBits = new ArrayList<>();
//        int totalOnes = 0;
//
//        for (int bit = 0; bit < maxBit; bit++) {
//            int countOnes = 0;
//            int firstOneIdx = -1;
//            for (int j = 0; j < n; j++) {
//                if (b[j].getBit(bit)) {
//                    countOnes++;
//                    if (firstOneIdx == -1) firstOneIdx = j;
//                }
//            }
//
//            totalOnes += countOnes;
//
//            if (countOnes == 0) {
//                zeroBits.add(bit);
//            } else if (countOnes % 2 == 1) {
//                boolean fixed = false;
//                for (int j = n - 1; j >= 0; j--) {
//                    if (b[j].getBit(bit + 1) && !b[j].getBit(bit)) {
//                        b[j].setBit(bit);
//                        totalOnes++;
//                        countOnes++;
//                        for (int k = bit + 1; k < maxBit; k++) {
//                            if (b[j].getBit(k)) {
//                                b[j].clearBit(k);
//                                break;
//                            }
//                        }
//                        fixed = true;
//                        break;
//                    }
//                }
//
//                if (!fixed && countOnes % 2 == 1) {
//                    for (int k = bit + 1; k < maxBit; k++) {
//                        if (!b[firstOneIdx].getBit(k)) {
//                            b[firstOneIdx].clearBit(bit);
//                            b[firstOneIdx].setBit(k);
//                            countOnes--;
//                            totalOnes--;
//                            break;
//                        }
//                    }
//                }
//
//                if (countOnes % 2 == 1) {
//                    oddBits.add(bit);
//                }
//            }
//        }
//
//        if (oddBits.size() % 2 == 1 || totalOnes % 2 == 1) {
//            System.out.println("impossible");
//            return;
//        }
//
//        if (!oddBits.isEmpty()) {
//            List<Integer> list = new ArrayList<>(oddBits);
//            list.addAll(zeroBits);
//            list.addAll(zeroBits);  // запас
//
//            for (int i = 0; i < n && !list.isEmpty(); i++) {
//                int pos0 = -1, pos1 = -1;
//                Iterator<Integer> it = list.iterator();
//                while (it.hasNext()) {
//                    int bit = it.next();
//                    if (b[i].getBit(bit)) {
//                        if (pos0 != -1) {
//                            b[i].clearBit(bit);
//                            b[i].setBit(pos0);
//                            it.remove();
//                            list.remove((Integer) bit);
//                            break;
//                        } else {
//                            pos1 = bit;
//                        }
//                    } else {
//                        if (pos1 != -1) {
//                            b[i].clearBit(pos1);
//                            b[i].setBit(bit);
//                            it.remove();
//                            list.remove((Integer) bit);
//                            break;
//                        } else {
//                            pos0 = bit;
//                        }
//                    }
//                }
//            }
//
//            if (!list.isEmpty()) {
//                System.out.println("impossible");
//                return;
//            }
//        }
//
//        Arrays.sort(b, Comparator.comparingInt(v -> v.index));
//        for (int i = 0; i < n; i++) {
//            System.out.print(b[i].toLong() + (i == n - 1 ? "\n" : " "));
//        }
//    }
//}
//📈 Преимущества этой реализации:
//Код стал чище и модульнее.
//
//BitSet даёт удобство работы с битами и масштабируемость под 128+ бит.
//
//Логика сохранена и улучшена.
//
//Хочешь, чтобы я добавил версию с профилированием времени или генерацией случайных тестов для бенчмарка?
//
//
//
//
//
//
//
//
//
//
//
//
//ChatGPT может допускать ошибки. Проверьте важную информацию. См. настройки cookie-файлов.
//?
