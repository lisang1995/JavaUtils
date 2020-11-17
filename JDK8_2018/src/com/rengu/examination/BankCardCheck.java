package com.rengu.examination;

/**
 * 校验银行卡卡号
 * Create by lisang on 2018/10/6.
 */
public class BankCardCheck {

    public static void main(String[] args) {
        //list
        String cardId = "49927398716";
        if (checkBankCard(cardId)) {
            System.out.println("卡号校验成功");
        } else {
            System.out.println("卡号校验失败");
        }
    }

    /**
     * 校验银行号的实现方法
     *
     * @param cardId 包含校验码的银行卡卡号
     * @return true 卡号校验通过  false 卡号校验失败
     */
    public static boolean checkBankCard(String cardId) {
        //检验卡号是否为空，是否是数字，去除空格是否有数据
        if (cardId == null || cardId.trim().length() == 0 || !cardId.matches("\\d+")) {
            return false;
        }
        char[] c = cardId.trim().toCharArray();
        int sum = 0;
        for (int i = c.length - 2, j = 0; i >= 0; i--, j++) {
            int k = c[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            sum += k;
        }
        int lastSum = sum + Integer.valueOf(cardId.substring(cardId.length() - 1));
        return (sum / 10 + 1) * 10 == lastSum;
    }
}
