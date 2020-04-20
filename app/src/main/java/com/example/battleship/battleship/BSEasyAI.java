package com.example.battleship.battleship;

import com.example.battleship.GameFramework.GameComputerPlayer;
import com.example.battleship.GameFramework.infoMessage.GameInfo;

public class BSEasyAI extends GameComputerPlayer {
  public BSEasyAI(String name) {super (name);}

  @Override
  protected void receiveInfo(GameInfo info) {
    if(info instanceof BSGameState) {
      if (((BSGameState) info).inGame == false) {
        game.sendAction(new BSPlaceCPUShip(this, ((int) (Math.random()) * 19 + 1)));
      }
      if (((BSGameState) info).getTurnCode() == 0) {
        return;
      }

      int randX, randY;

      do {
        randX = randomX();
        randY = randomY();
      } while (((BSGameState) info).humanPlayerBoard[randX][randY] == 1 ||
              ((BSGameState) info).humanPlayerBoard[randX][randY] == 2);

      sleep(.75);

      game.sendAction(new BSFire(this, randX, randY));
    }
    else {
      return;
    }

  }

  public int randomX() {
    return (int) (Math.random() * 10);
  }
  public int randomY() {
    return  (int) (Math.random() * 10);
  }

}
