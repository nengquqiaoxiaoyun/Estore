package cn.web.utils;

import java.util.UUID;

/**
 * ��ֹ�ļ����ظ�UUID��д�ļ����ķ���
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}