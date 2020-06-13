package com.example.kiki.quiztest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hntbad.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("You take a woman back to your place. You start making out, things get hot and heavy, but " +
                "before you know it she stops saying she has to leave. You...",
                "Make her stay. What makes her think she can get you aroused then leave you hanging?",
                "Tell her 'Okay' but complain about your 'blue balls'. ",
                "Walk her to the door and wish her a good night acknowledging she doesn't owe you sex.",
                3);
        addQuestion(q1);
        Question q2 = new Question("You see a cute woman at the bar who has had one too many. You...",
                "Try to find who she came with. You know men can take advantage of woman that are intoxicated.",
                "Buy her more drinks.",
                "Try to take her home.",
                1);
        addQuestion(q2);
        Question q3 = new Question("You hear of another rape allegation against a celebrity. You think...",
                "It may have happened but what did the accuser do to tempt him?",
                "The accuser is telling the truth",
                "The accuser is lying and only doing this for money",
                2);
        addQuestion(q3);
        Question q4 = new Question("A woman at work, who is well endowed, regularly wears low cut shirts. You...",
                "Ask another woman co-worker to ask the other co-worker to wear higher tops.",
                "Tell your co-worker her breasts look fantastic but she needs to cover them up.",
                "Nothing. She can do with her breasts as she sees fit. Her breasts are not to tempt you.",
                3);
        addQuestion(q4);
        Question q5 = new Question("You friend is joking about grabbing women by the pussy. You...",
                "Tell him it isn't funny and even joking about it is dangerous.",
                "Laugh about it.",
                "Say nothing.",
                1);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
