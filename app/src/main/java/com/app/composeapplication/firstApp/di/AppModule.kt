package com.app.composeapplication.firstApp.di

import android.app.Application
import androidx.room.Room
import com.app.composeapplication.fifthApp.data.*
import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.reppository.MapRepository
import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.reppository.MapRepositoryImpl
import com.app.composeapplication.fifthApp.ui.screens.noteViewModel.MyNoteRepositoy
import com.app.composeapplication.firstApp.ui.feture_note.Note.GetNote
import com.app.composeapplication.firstApp.ui.feture_note.data.repositry.NoteRepositoryImple
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.AddNote
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.DeleteNote
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.GetNotesUseCase
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.NoteUseCase
import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.NoteDataBaseClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDataBaseClass{
        return Room.databaseBuilder(application
        , NoteDataBaseClass::class.java,
        NoteDataBaseClass.dataBasename).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDataBaseClass):NoteRepositroy{
        return NoteRepositoryImple(db.noteDao)
    }

//    @Provides
//    @Singleton
//    fun providesMyNoteRepositoy(db: NoteDataBaseClass):NoteRepositroy{
//        return MyNoteRepositoy(db.noteDao)
//    }

    @Provides
    @Singleton
    fun provideNoteuseCase(repositroy: NoteRepositroy): NoteUseCase{
        return NoteUseCase(
            getNotesUseCase = GetNotesUseCase(repositroy),
            deleteNote = DeleteNote(repositroy),
            addNote = AddNote(repositroy),
            getNote = GetNote(repositroy)
        )
    }
    @Provides
    @Singleton
    fun provideMYNoteuseCase(repositroy: NoteRepositroy): MyNoteUseCase{
        return MyNoteUseCase(
            getMyNotUseCase = GetMyNotUseCase(repositroy),
            deleteNotUseCase = MyDeleteNotUseCase(repositroy),
            addMyNotes = AddMyNotes(repositroy),
            getNotbyId = GetNotbyId(repositroy)
        )
    }

    @Provides
    @Singleton
    fun providesParkingSpotRepository(db:NoteDataBaseClass): MapRepository{
        return MapRepositoryImpl(db.noteDao)
    }

}