/*
 * 作者：刘时明
 * 时间：2020/3/21-1:34
 * 作用：
 */
package com.lsm1998.chat.auto.sound;

import com.lsm1998.chat.auto.AutoStart;
import javazoom.jl.player.Player;
import lombok.Data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Data
public class SoundStart implements AutoStart
{
    private List<String> musicList = new ArrayList<>();
    private int playIndex;
    private SoundProperties soundProperties;

    @Override
    public void initStart()
    {
        String fileDir = SoundStart.class.getResource("/" + soundProperties.getDir()).getFile();
        File file = new File(fileDir);
        if (file.exists())
        {
            File[] files = file.listFiles();
            if (files == null)
            {
                return;
            }
            for (File f : files)
            {
                musicList.add(f.getAbsolutePath());
            }
        }
        if (soundProperties.getPlay() && musicList.size() > 0)
        {
            play();
        }
    }

    private void play()
    {
        String first = musicList.get(playIndex);
        play(first);
    }

    private void play(String filePath)
    {
        Player player = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream))
        {
            player = new Player(bufferedInputStream);
            player.play();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (player != null)
            {
                player.close();
            }
        }
        if (soundProperties.getPool() && playIndex == musicList.size() - 1)
        {
            playIndex = 0;
            play();
        } else
        {
            play(musicList.get(++playIndex));
        }
    }

//    public void play(String filePath)
//    {
//        filePath = RESOURCES + "\\sound\\" + filePath + ".mp3";
//        Player player = null;
//        try
//        {
//            player = new Player(new BufferedInputStream(new FileInputStream(new File(filePath))));
//            player.play();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        } finally
//        {
//            if (player != null)
//                player.close();
//        }
//    }
}
