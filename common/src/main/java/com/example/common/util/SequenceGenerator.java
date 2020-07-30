package com.example.common.util;

/**
 * @author Luo Bao Ding
 * @since 2019/11/21
 */
public class SequenceGenerator {
    private static final ProtectedSequenceGenerator sequenceGenerator = new ProtectedSequenceGenerator();

    private SequenceGenerator() {
    }

    public static long nextId() {
        return sequenceGenerator.nextId();
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        long max = 9007199254740992L;
        for (int i = 0; i < 100; i++) {
            long current = nextId();
            System.out.println(current);
            if (current > max) {
                System.out.println("id:" + current + "超过最大限制,已生成" + (i + 1) + "次");
            }
        }
    }

}
