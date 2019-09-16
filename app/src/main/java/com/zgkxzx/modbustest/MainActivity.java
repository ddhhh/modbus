package com.zgkxzx.modbustest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zgkxzx.modbus4And.requset.ModbusParam;
import com.zgkxzx.modbus4And.requset.ModbusReq;
import com.zgkxzx.modbus4And.requset.OnRequestBack;

import java.util.Arrays;

public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnConnect = findViewById(R.id.btn_connect);
        final EditText edtIp = findViewById(R.id.edt_ip);
        final EditText edtPort = findViewById(R.id.edt_port);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = edtIp.getText().toString();
                int port = Integer.parseInt(edtPort.getText().toString());
                modbusInit(ip, port);
            }
        });


    }

    private void modbusInit(String ip, int port) {
        ModbusReq.getInstance().setParam(new ModbusParam()
                .setHost(ip)
                .setPort(port)
                .setEncapsulated(false)
                .setKeepAlive(true)
                .setTimeout(2000)
                .setRetries(0))
                .init(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                showToast("连接成功:"+ s);

            }

            @Override
            public void onFailed(String msg) {
                showToast("连接失败:"+ msg);

            }
        });


    }

    public void readCoilClickEvent(View view) {
        //0000 0000 0006 0101 0001 0002
        ModbusReq.getInstance().readCoil(new OnRequestBack<boolean[]>() {
            @Override
            public void onSuccess(boolean[] booleans) {
                Log.d(TAG, "readCoil onSuccess " + Arrays.toString(booleans));
                showToast("readCoil onSuccess " + Arrays.toString(booleans));
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "readCoil onFailed " + msg);
                showToast("readCoil onFailed " + msg);
            }
        }, 1, 1, 2);


    }

    public void readDiscreteInputClickEvent(View view) {

        ModbusReq.getInstance().readDiscreteInput(new OnRequestBack<boolean[]>() {
            @Override
            public void onSuccess(boolean[] booleen) {
                Log.d(TAG, "readDiscreteInput onSuccess " + Arrays.toString(booleen));
                showToast("readDiscreteInput onSuccess " + Arrays.toString(booleen));
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "readDiscreteInput onFailed " + msg);
                showToast("readDiscreteInput onFailed " + msg);
            }
        }, 1, 1, 5);


    }

    public void readHoldingRegistersClickEvent(View view) {

        //readHoldingRegisters
        ModbusReq.getInstance().readHoldingRegisters(new OnRequestBack<short[]>() {
            @Override
            public void onSuccess(short[] data) {
                Log.d(TAG, "readHoldingRegisters onSuccess " + Arrays.toString(data));
                showToast("readHoldingRegisters onSuccess " + Arrays.toString(data));
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "readHoldingRegisters onFailed " + msg);
                showToast("readHoldingRegisters onFailed " + msg);
            }
        }, 1, 2, 8);


    }

    public void readInputRegistersClickEvent(View view) {


        ModbusReq.getInstance().readInputRegisters(new OnRequestBack<short[]>() {
            @Override
            public void onSuccess(short[] data) {
                Log.d(TAG, "readInputRegisters onSuccess " + Arrays.toString(data));
                showToast("readInputRegisters onSuccess " + Arrays.toString(data));
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "readInputRegisters onFailed " + msg);
                showToast("readInputRegisters onFailed " + msg);
            }
        }, 1, 2, 8);


    }

    public void writeCoilClickEvent(View view) {


        ModbusReq.getInstance().writeCoil(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "writeCoil onSuccess " + s);
                showToast("writeCoil onSuccess " + s);
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "writeCoil onFailed " + msg);
                showToast("writeCoil onFailed " + msg);
            }
        }, 1, 1, true);


    }

    public void writeRegisterClickEvent(View view) {

        ModbusReq.getInstance().writeRegister(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "writeRegister onSuccess " + s);
                showToast("writeRegister onSuccess " + s);
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "writeRegister onFailed " + msg);
                showToast("writeRegister onFailed " + msg);
            }
        }, 1, 1, 234);


    }

    public void writeRegistersClickEvent(View view) {

        ModbusReq.getInstance().writeRegisters(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "writeRegisters onSuccess " + s);
                showToast("writeRegisters onSuccess " + s);
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "writeRegisters onFailed " + msg);
                showToast("writeRegisters onFailed " + msg);
            }

        }, 1, 2, new short[]{211, 52, 34});


    }


    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
