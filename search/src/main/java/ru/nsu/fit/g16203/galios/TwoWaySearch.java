package ru.nsu.fit.g16203.galios;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TwoWaySearch {

    public List<Long> indexOf(byte[] x, byte[] y, int n) {

        List<Long> positions = new LinkedList<>();
        int[] p = new int[1];
        int[] q = new int[1];

        int ell, per, memory;

        int m = x.length;

        int i = maxSuf(x, m, p);
        int j = maxSufTilde(x, m, q);

        if (i > j) {
            ell = i;
            per = p[0];
        } else {
            ell = j;
            per = q[0];
        }

        if (Arrays.equals(x, 0, ell + 1, x, per, per + ell + 1)) {
            j = 0;
            memory = -1;
            while (j <= n - m) {
                i = Math.max(ell, memory) + 1;
                while (i < m && x[i] == y[i + j]) {
                    ++i;
                }
                if (i >= m) {
                    i = ell;
                    while (i > memory && x[i] == y[i + j]) {
                        --i;
                    }
                    if (i <= memory) {
                        positions.add((long)j);
                    }
                    j += per;
                    memory = m - per - 1;
                } else {
                    j += (i - ell);
                    memory = -1;
                }
            }
        } else {
            per = Math.max(ell + 1, m - ell - 1) + 1;
            j = 0;
            while (j <= n - m) {
                i = ell + 1;
                while (i < m && x[i] == y[i + j]) {
                    ++i;
                }
                if (i >= m) {
                    i = ell;
                    while (i >= 0 && x[i] == y[i + j]) {
                        --i;
                    }
                    if (i < 0) {
                        positions.add((long)j);
                    }
                    j += per;
                } else {
                    j += i - ell;
                }
            }
        }
        return positions;
    }


    private int maxSuf(byte[] x, int m, int[] p) {
        int ms, j, k;
        byte a, b;

        ms = -1;
        j = 0;
        k = p[0] = 1;
        while (j + k < m) {
            a = x[j + k];
            b = x[ms + k];
            if (a < b) {
                j += k;
                k = 1;
                p[0] = j - ms;
            } else if (a == b)
                if (k != p[0])
                    ++k;
                else {
                    j += p[0];
                    k = 1;
                }
            else { /* a > b */
                ms = j;
                j = ms + 1;
                k = p[0] = 1;
            }
        }
        return (ms);
    }

    private int maxSufTilde(byte[] x, int m, int[] p) {
        int ms, j, k;
        byte a, b;

        ms = -1;
        j = 0;
        k = p[0] = 1;
        while (j + k < m) {
            a = x[j + k];
            b = x[ms + k];
            if (a > b) {
                j += k;
                k = 1;
                p[0] = j - ms;
            } else if (a == b)
                if (k != p[0])
                    ++k;
                else {
                    j += p[0];
                    k = 1;
                }
            else { /* a < b */
                ms = j;
                j = ms + 1;
                k = p[0] = 1;
            }
        }
        return (ms);
    }


}
