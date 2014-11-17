package pt.up.fe.droidbeiro.Presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import pt.up.fe.droidbeiro.R;

public class BombeiroMain extends Activity {

    // Initialize the array
    String[] messages = {   "Preciso de ajuda",
                            "Preciso afastar-me",
                            "Preciso de suporte aéreo",
                            "Fogo a espalhar-se",
                            "Fogo perto de casa",
                            "Camião com problemas",
                            "Casa queimada",
                            "A retirar-me",
                        };

    // Declare the UI components
    private ListView lista_mensagens_layout;
    private ArrayAdapter arrayAdapter;
    private Button btn_enviar_msg;
    private Button btn_modo_combate;
    private String mensagem;
    private EditText custom_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bombeiro_main);

        // Hiding the action bar
        getActionBar().hide();

        lista_mensagens_layout = (ListView) findViewById(R.id.lista_mensagens);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);
        lista_mensagens_layout.setAdapter(arrayAdapter);

        custom_message=(EditText)findViewById(R.id.msg_custom);
        btn_enviar_msg = (Button)findViewById(R.id.btn_enviar_msg);
        btn_modo_combate= (Button)findViewById(R.id.btn_modo_combate);

        lista_mensagens_layout.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {//    if (!msg_type) {
                custom_message.setText("");
                mensagem = lista_mensagens_layout.getItemAtPosition(position).toString();
                custom_message.setText(mensagem);
            }
        });

        btn_enviar_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mensagem = custom_message.getText().toString().trim();


                if (!(mensagem.isEmpty())) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(BombeiroMain.this);
                    alertDialog.setTitle("Enviar mensagem ?");
                    alertDialog.setMessage(mensagem);

                    //Setting Positive "Sim" Button
                    alertDialog.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //envia a mensagem para o centro de controlo
                            custom_message.setText("");
                        }
                    });

                    // Setting Negative "NÃO" Button
                    alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //envia a mensagem para o centro de controlo
                            custom_message.setText("");
                        }
                    });
                    alertDialog.show();
                }else{
                    Toast.makeText(getApplicationContext(), "Nada a enviar", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_modo_combate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Used to test
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BombeiroMain.this);
                //Setting Dialog Title
                alertDialog.setTitle("Modo de Combate");
                //Setting Dialog Message
                alertDialog.setMessage("Entrar em Combate?");

                //Setting Positive "Sim" Button
                alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke SIM event

                        //Start NewActivity.class
                        Intent myIntent = new Intent(BombeiroMain.this,
                                BombeiroMC.class);
                        startActivity(myIntent);
                    }
                });

                // Setting Negative "NÃO" Button
                alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NÃO event
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bombeiro_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
