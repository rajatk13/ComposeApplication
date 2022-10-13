package com.app.composeapplication.fifthApp.di

import android.app.Application
import androidx.room.Room

import com.app.composeapplication.firstApp.ui.roomDB.NoteDataBaseClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyAppModule {


    /*@Provides
    @Singleton
    fun providesMyNoteDataBase(application: Application):MyNoteDatabase{
        return Room.databaseBuilder(
            application,
            MyNoteDatabase::class.java,
            MyNoteDatabase.myDatabaseName).fallbackToDestructiveMigration()
            .build()

    }*/

    //@Binds
//    @Provides
//    @Singleton
//    fun providesMyNotRepository(db:NoteDataBaseClass): CommonRepository {
//        return MyNotRepository(db.noteDao)
//    }
   /* @Provides
    @Singleton
    fun provideNoteuseCasee(repositroy: NoteRepositroy): NoteUseCase {
        return NoteUseCase(
            getNotesUseCase = GetNotesUseCase(repositroy),
            deleteNote = DeleteNote(repositroy),
            addNote = AddNote(repositroy),
            getNote = GetNote(repositroy)
        )
    }*/


}