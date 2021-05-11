package app.doggy.drum

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //SoundPoolの変数
    private lateinit var mSoundPool: SoundPool

    //ロードした音声の配列
    private lateinit var mSoundIds: Array<Int?>

    //音声ファイルの配列
    private val mSoundResource = listOf(
        R.raw.cymbal1,
        R.raw.cymbal2,
        R.raw.cymbal3,
        R.raw.tom1,
        R.raw.tom2,
        R.raw.tom3,
        R.raw.hihat,
        R.raw.snare,
        R.raw.bass
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ImageViewの配列
        val images = listOf(
            cymbal1Image,
            cymbal2Image,
            cymbal3Image,
            tom1Image,
            tom2Image,
            tom3Image,
            hihatImage,
            snareImage,
            bassImage
        )

        //クリックリスナを設定
        for (i in 0 until images.size) {
            images[i].setOnClickListener(MusicClickListener())
        }

    }

    override fun onResume() {
        super.onResume()

        //ステップ1
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        //ステップ2
        mSoundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(mSoundResource.size)
            .build()

        //配列を初期化
        mSoundIds = arrayOfNulls(0)

        //ステップ3 音声をロード
        for(i in 0 until mSoundResource.size) {
            mSoundIds += mSoundPool.load(baseContext, mSoundResource[i], 0)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //アプリとして音源を抱えたままになり、Out Of memoryにならない対策
        mSoundPool.release()
    }

    private inner class MusicClickListener: View.OnClickListener{
        override fun onClick(view: View) {
            //ステップ4 音声を再生（play(ロードしたID, 左音量, 右音量, 優先度, ループ, 再生速度)）
            when(view.id) {
                R.id.cymbal1Image -> mSoundPool.play(mSoundIds[0] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.cymbal2Image -> mSoundPool.play(mSoundIds[1] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.cymbal3Image -> mSoundPool.play(mSoundIds[2] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.tom1Image -> mSoundPool.play(mSoundIds[3] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.tom2Image -> mSoundPool.play(mSoundIds[4] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.tom3Image -> mSoundPool.play(mSoundIds[5] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.hihatImage -> mSoundPool.play(mSoundIds[6] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.snareImage -> mSoundPool.play(mSoundIds[7] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
                R.id.bassImage -> mSoundPool.play(mSoundIds[8] as Int, 1.0f, 1.0f, 0, 0, 1.0f)
            }
        }
    }
}