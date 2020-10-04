package test.algorithm;

public class FenZhiHeBingPaiXu {
    /**
     * 函数说明：在数组被拆分以后进行合并
     */
    static void Merge(int a[], int left, int middle, int rigth) {
        //定义左端数组大小
        int n1 = middle - left+1;
        int n2 = rigth - middle;

        //初始化数组，分配内存
        int begin[] = new int[n1];
        int end[] = new int[n2];

        //数组赋值
        for(int i = 0; i < n1; i++)
            begin[i] = a[left + i];

        for(int i = 0; i < n2; i++)
            end[i] = a[middle+1+i];

        //用key做原数组索引，没调用一次函数重新给原数组付一次值
        int i = 0, j = 0, key;
        for(key = left; key <= rigth; key++){

            if(n1>i&&n2>j&&i < n1 && begin[i] <= end[j])
                a[key] = begin[i++];
            else if(n1>i&&n2>j&&j < n2 && begin[i] >= end[j])
                a[key] = end[j++];
            else if(i == n1 && j < n2)
                a[key] = end[j++];
            else if(j == n2 && i < n1)
                a[key] = begin[i++];
        }
    }
    /**
     * 差分数组区间，不断分支
     */
    static void MergeSort(int a[],int left,int rigth) {
        int middle=0;
        if(left<rigth) {
            middle =(rigth+left)/2;
            MergeSort(a, left, middle);
            MergeSort(a, middle+1, rigth);
            Merge(a, left, middle, rigth);
        }
    }
    public static void main(String[] args) {
        int a[]= {85,3,52,9,7,1,5,4};
        MergeSort(a, 0,7);
        for(int i=0;i<8;i++) {
            System.out.print(" "+a[i]);
        }

    }

}
