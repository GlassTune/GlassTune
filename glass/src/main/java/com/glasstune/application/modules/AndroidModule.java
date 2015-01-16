package com.glasstune.application.modules;

/**
 * Created by njackson on 29/12/14.
 */

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.glasstune.activities.TuneGuitarActivity;
import com.glasstune.audio.IPitchDetection;
import com.glasstune.audio.TASOSAudioDetection;
import com.glasstune.utils.AlertDialogBuilder;
import com.glasstune.utils.IAlertDialogBuilder;

/**
 * Created by server on 30/03/2014.
 */
@Module(library = true,complete=false,injects = {TuneGuitarActivity.class})
public class AndroidModule {
    @Provides @Singleton
    IPitchDetection providePitchDetection() {
        return new TASOSAudioDetection();
    }

    @Provides
    IAlertDialogBuilder provideAlertDialogBuilder() {return new AlertDialogBuilder(); }
}
