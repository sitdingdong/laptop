package sorting;

import java.util.Arrays;

public class heap {
    /**
     * �����󶥶�
     */
    public static void adjustHeap(int[] a, int i, int len) {
        int temp, j;
        temp = a[i];
        for (j = 2 * i + 1; j < len; j = 2 * j + 1) {// �عؼ�����������ɸѡ
            if (j < len - 1  && a[j] < a[j + 1])
                j++; // jΪ�ؼ����нϴ��¼���±�
            if (temp >= a[j])
                break;
            a[i] = a[j];
            i = j;
        }
        a[i] = temp;
    }

    public static void heapSort(int[] a) {
        int i;
        for (i = a.length / 2 - 1; i >= 0; i--) {// ����һ���󶥶�
            adjustHeap(a, i, a.length - 1);
        }
        for (i = a.length - 1; i >= 0; i--) {// ���Ѷ���¼�͵�ǰδ�����������е����һ����¼����
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            adjustHeap(a, 0, i);// ��β����
        }
    }

    public static void main(String[] args) {
        int a[] = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50, 51 };
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
} 