package net.ricardoviana.romans.v05;

public class Romans {

    String[] rTpl = new String[]{"0", "01", "011", "0111", "15"};
    byte[][] cOrder = new byte[][]{{0, 1, 3}, {0, 2, 1}};
    String[] rLet = new String[]{"IVX ", "XLC ", "CDM ", "M   "};

    public String from(int num) {
        if (num <= 0 || num >= 4000) throw new IllegalArgumentException("arg: " + num);
        StringBuilder sb = new StringBuilder();
        for (int d = 4; d-- >= 1;) {
            int value = (int) (num / Math.pow(10, d) % 10);
            sb.append(cvt(rTpl[value % 5], cOrder[value / 5], rLet[d].toCharArray()));
        }
        return sb.toString();
    }

    private String cvt(String tpl, byte[] p, char[] c) {
        for (int i = -1; i++ < 2;) tpl = tpl.replace("150".charAt(i), c[p[i]]);
        return tpl.trim();
    }
}
