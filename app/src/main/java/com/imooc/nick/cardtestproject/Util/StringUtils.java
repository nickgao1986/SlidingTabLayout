package com.imooc.nick.cardtestproject.Util;

import org.json.JSONArray;
import org.json.JSONObject;

public class StringUtils {

    public static JSONArray getJsonArray(String[] fileName) throws Exception {
        if (fileName == null || fileName.length == 0) {
            return null;
        }
        JSONArray array = new JSONArray();
        for (int i = 0; i < fileName.length; i++) {
            String file = fileName[i];
            if (!StringUtils.isNull(file)) {
                array.put(file);
            }
        }
        return array;
    }

    public static JSONArray getJsonArray(JSONObject object, String key) {
        JSONArray isonArray = new JSONArray();
        try {
            if (object == null || key == null)
                return null;
            if (object.has(key)) {
                Object obj = object.getJSONArray(key);
                if (obj != null) {
                    isonArray = (JSONArray) obj;
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return isonArray;

    }

    public static String getJsonString(JSONObject jsonObject, String key) {
        try {
            if (jsonObject.has(key))
                return jsonObject.getString(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static boolean isNull(String str) {
        try {
            if (str == null) {
                return true;
            } else if (str != null) {
                if (str.equals("") || str.equals("null") || str.equals("[]")) {
                    return true;
                } else if (str.trim().equals("") || str.trim().equals("null")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static double getJsonDouble(JSONObject object, String key) {
        double strValue = 0d;
        try {
            if (object == null || key == null)
                return strValue;
            if (object.has(key)) {
                strValue = object.getDouble(key);
            }

            return strValue;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strValue;
    }

    public static int getJsonInt(JSONObject jsonObject, String key) {
        try {
            if (StringUtils.isNull(key))
                return 0;
            if (jsonObject.has(key))
                return jsonObject.getInt(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int getJsonIntSetDefault(JSONObject object, String key, int defaultValue) {
        int strValue = defaultValue;
        try {
            if (object == null || key == null)
                return strValue;
            if (object.has(key)) {
                strValue = object.getInt(key);
            }
            return strValue;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strValue;
    }

    public static boolean getJsonBoolean(JSONObject object, String key) {
        Boolean strValue = false;
        try {
            if (object == null || key == null)
                return strValue;
            if (object.has(key)) {
                strValue = object.getBoolean(key);
            }

            return strValue;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strValue;
    }

    public static boolean getJsonBoolean(JSONObject object, String key, boolean defalut) {
        Boolean strValue = defalut;
        try {
            if (object == null || key == null)
                return strValue;
            if (object.has(key)) {
                strValue = object.getBoolean(key);
            }

            return strValue;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strValue;
    }

    public static int getInt(String value) {
        try {
            if (value == null || value.equals("") || value.equals("null"))
                return 0;

            return Integer.valueOf(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static float getFloat(String value) {
        try {
            return Float.valueOf(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static String getString(int value) {
        try {
            return String.valueOf(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getString(float value) {
        try {
            return String.valueOf(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }

    public static JSONObject getJsonObejct(JSONObject object, String key) {
        JSONObject jsonObject = null;
        try {
            if (object == null || key == null)
                return null;
            if (object.has(key)) {
                Object obj = object.getJSONObject(key);
                if (obj != null) {
                    jsonObject = (JSONObject) obj;
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return jsonObject;

    }

}
