package com.kinghy.rag.common;

public enum ChatType {
    RAG("rag"),
    SIMPLE("simple");
    private final String describe;

    ChatType(String describe){
        this.describe = describe;
    }

    public String getDescribe(){
        return this.describe;
    }


    public static ChatType getChatType(String chatTypeStr){
        ChatType[] values = ChatType.values();
        for(ChatType chatType: values){
            if(chatType.getDescribe().equals(chatTypeStr)){
                return chatType;
            }
        }
        return null;
    }
}
