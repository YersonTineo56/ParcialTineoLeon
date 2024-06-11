package com.example.parcialpractica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_game extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Filas
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columnas
            {0, 4, 8}, {2, 4, 6}             // Diagonales
    };
    private String currentPlayer = "X";
    private String[] board = new String[9];
    private TextView textViewWelcome;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonBack = findViewById(R.id.buttonBack);

        // Obtener el nombre de usuario desde el Intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        textViewWelcome.setText("Bienvenido " + username);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(index);
                }
            });
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresar a la actividad anterior
            }
        });
    }

    private void onButtonClick(int index) {
        // Verificar si la celda ya está ocupada
        if (board[index] != null) {
            return; // Salir del método si la celda ya está ocupada
        }

        // Contar cuántas celdas ocupadas hay del mismo tipo
        int countCurrentPlayer = 0;
        for (String cell : board) {
            if (cell != null && cell.equals(currentPlayer)) {
                countCurrentPlayer++;
            }
        }

        // Si hay más de 3 celdas ocupadas del mismo tipo, eliminar los valores más antiguos del mismo tipo
        if (countCurrentPlayer >= 3) {
            int oldestIndex = -1;
            for (int i = 0; i < board.length; i++) {
                if (board[i] != null && board[i].equals(currentPlayer)) {
                    if (oldestIndex == -1) {
                        oldestIndex = i;
                    } else {
                        // Eliminar el valor más antiguo del mismo tipo
                        board[oldestIndex] = null;
                        buttons[oldestIndex].setText(""); // Limpiar el texto del botón
                        oldestIndex = i;
                    }
                }
            }
        }

        // Colocar el nuevo valor en la celda seleccionada
        board[index] = currentPlayer;
        buttons[index].setText(currentPlayer); // Establecer el texto del botón con el valor actual

        // Verificar si hay un ganador
        if (checkForWin()) {
            Toast.makeText(this, "El jugador " + currentPlayer + " ha ganado!", Toast.LENGTH_SHORT).show();
            resetBoard(); // Reiniciar el tablero si hay un ganador
        } else if (isBoardFull()) { // Verificar si hay un empate
            Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
            resetBoard(); // Reiniciar el tablero si hay un empate
        } else {
            // Cambiar al siguiente jugador
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private boolean checkForWin() {
        for (int[] winPosition : winningPositions) {
            if (board[winPosition[0]] != null &&
                    board[winPosition[0]].equals(board[winPosition[1]]) &&
                    board[winPosition[0]].equals(board[winPosition[2]])) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (String cell : board) {
            if (cell == null) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = null;
            buttons[i].setText("");
        }
        currentPlayer = "X";
    }
}

