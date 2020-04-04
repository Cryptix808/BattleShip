package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GameComputerPlayer;
import com.example.battleship.GameFramework.infoMessage.GameInfo;

public class BSEasyAI extends GameComputerPlayer {
  public BSEasyAI(String name) {super (name);}

  @Override
  protected void receiveInfo(GameInfo info) {
    if(info instanceof BSGameState){

    }
  }
}
