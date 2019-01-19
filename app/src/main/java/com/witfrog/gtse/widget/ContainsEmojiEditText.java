package com.witfrog.gtse.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.blankj.utilcode.util.ToastUtils;

public class ContainsEmojiEditText extends AppCompatEditText {

    /**
     * 输入表情前的光标位置
     */
    private int     mCursorPos;
    /**
     * 输入表情前EditText中的文本
     */
    private String  mInputAfterText;
    /**
     * 是否重置了EditText的内容
     */
    private boolean mResetText;

    public ContainsEmojiEditText(Context context) {
        super(context);
        initEditText();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditText();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditText();
    }

    /**
     * 检测是否有emoji表情
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            //如果不能匹配,则该字符是Emoji表情
            if (!isEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return codePoint == 0x0 || codePoint == 0x9 || codePoint == 0xA || codePoint == 0xD || codePoint >= 0x20 && codePoint <= 0xD7FF || codePoint >= 0xE000 && codePoint <= 0xFFFD;
    }

    /**
     * 初始化EditText控件
     */
    private void initEditText() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                if (!mResetText) {
                    mCursorPos = getSelectionEnd();
                    // 这里用s.toString()而不直接用s是因为如果用s，那么inputAfterText和s在内存中指向的是同一个地址，
                    // s改变了，inputAfterText也就改变了，那么表情过滤就失败了
                    mInputAfterText = s.toString();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!mResetText) {
                        //表情符号的字符长度最小为2
                        if (count >= 2) {
                            CharSequence input = s.subSequence(mCursorPos, mCursorPos
                                    + count);
                            if (containsEmoji(input.toString())) {
                                mResetText = true;
                                ToastUtils.showShort("Don't support input Emoji.");
                                //是表情符号就将文本还原为输入表情符号之前的内容
                                setText(mInputAfterText);
                                CharSequence text = getText();
                                if (text != null) {
                                    Spannable spanText = (Spannable) text;
                                    Selection.setSelection(spanText, text.length());
                                }
                            }
                        }
                    } else {
                        mResetText = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
