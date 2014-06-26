int waterSensor = A0; 
int tempSensor = A1;
int gasSensor = A5;
int waterValue = 0; 
int gasValue=0;
int tempValue=0;
//int trigger = 10;
//int echo=11;
int statusLedG=6;
int statusLedR=5;
int luxSensor=A2;
int laserSensorSx=12;
int laserSensorDx=13;
int tiltSensor=7;
int tiltValue=0;

void setup() {
  //pinMode(trigger,OUTPUT);
  //pinMode(echo,INPUT);
  pinMode(statusLedG,OUTPUT);
  pinMode(statusLedR,OUTPUT);
  pinMode(laserSensorSx,INPUT);
  pinMode(laserSensorDx,INPUT);
  pinMode(tiltSensor,INPUT);
  Serial.begin(9600); 
}

void loop() {
  if(Serial.available()){
    if(Serial.read()){
  int statoLaserSx = digitalRead(laserSensorSx);
  int statoLaserDx = digitalRead(laserSensorDx);
  int luce= analogRead(luxSensor);
  waterValue = analogRead(waterSensor); 
  gasValue= analogRead(gasSensor); 
  tempValue= readTemp(tempSensor);
  tiltValue= digitalRead(tiltSensor);
  Serial.print("{'TILT':'");
  Serial.print(tiltValue);
  Serial.print("','RETROSX':'");
  Serial.print(statoLaserSx);  
  Serial.print("','RETRODX':'");
  Serial.print(statoLaserDx);
  Serial.print("','LUCE':'" );  
  Serial.print(luce);
  Serial.print("','TEMP':'");
  Serial.print(tempValue);
  Serial.print("','GAS':'");
  Serial.print(gasValue); 
  Serial.print("','ACQUA':'");                       
  Serial.print(waterValue);
  Serial.println("'}");
  digitalWrite(statusLedR,HIGH);
  digitalWrite(statusLedG,LOW);
  delay(1000);     
  digitalWrite(statusLedR,LOW);  
  digitalWrite(statusLedG,HIGH);
  }
  }
}


float readTemp(int pinTemp)
{
  float temp = 0.0;       // valore convertito in temperatura (°C)
  int val = 0;            // valore quantizzato dall'ADC [0..1023]
  int nread = 5;          // numero di letture (da 5 a 8)
  float somma = 0.0;      // somma delle letture
  for (int i=0; i<nread; i++)
  {
    val = analogRead( pinTemp );              // legge il dato della tensione sul pin 'LM35_pin' 
    temp = ( 100.0 *  4.1 * val ) / 1024.0;   // lo converte in °C
    somma += temp;                             // aggiunge alla somma delle temperature lette   
  }   
  return ( somma / nread );                     // ne calcola il valore medio 
}

