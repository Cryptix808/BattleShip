package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GameComputerPlayer;
import com.example.battleship.GameFramework.infoMessage.GameInfo;

public class BSEasyAI extends GameComputerPlayer {
  public BSEasyAI(String name) {super (name);}

  @Override
  protected void receiveInfo(GameInfo info) {
    if(info instanceof BSGameState){
        if(((BSGameState) info).getPlayer() != 0){
          return;
        }
    }
    if(((BSGameState) info).inGame == false){
      game.sendAction(new BSPlaceCPUShip(this, (int)(Math.random() * 6)));
    }

    int randX, randY;

    do {
      randX = randomX();
      randY = randomY();
    } while (((BSGameState) info).humanPlayerBoard[randX][randY] != 0 ||
    ((BSGameState) info).humanPlayerBoard[randX][randY] != 3);

    sleep(.75);

    game.sendAction(new BSFire(this, randX, randY));

  }

  public int randomX() {
    return (int) (Math.random() * 10);
  }
  public int randomY() {
    return  (int) (Math.random() * 10);
  }

}
