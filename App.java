import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class CustomComparator implements Comparator<List<String>>
    {
        @Override
        public int compare(List<String> o1,
            List<String> o2)
        {
            String firstString_o1 = o1.get(1);
            String firstString_o2 = o2.get(1);
            return firstString_o1.compareTo(firstString_o2); 
        }
    }

public class App {
    static Boolean validation = false;    
    static Boolean validation2 = false;    
    static String kodeKaryawan;
    static int gajiKaryawan;
    static List<List<String>> arListKaryawan;

    public static void main(String[] args) throws Exception {
        Scanner userInput = new Scanner(System.in);
        
        
        arListKaryawan = new ArrayList<List<String>>();
        Boolean loop = true;

        while (loop) {
            System.out.println("===PT ChipiChapa===");
            System.out.println("1. Input Karyawan");
            System.out.println("2. View Data Karyawan");
            System.out.println("3. Update Data Karyawan");
            System.out.println("4. Delete Data Karyawan");
            System.out.print("Menu : ");
            int menu = userInput.nextInt();
            userInput.nextLine();

            switch (menu) {
                case 1:
                validation = false;
                validation2 = true;
                String namaKaryawan = new String();
                String jenisKelamin = new String();
                String jabatanKaryawan = new String();
                    System.out.println("===INPUT KARYAWAN===");
                    do {
                        genKodeKaryawan();
                        System.out.println("Kode Karyawan : " + kodeKaryawan);
                        validasiKodeKaryawan(kodeKaryawan);
                    } while (!validation2);

                    do {
                        System.out.print("Input nama karyawan [>=3 , Hanya boleh Huruf] : ");
                        namaKaryawan = userInput.nextLine();
                        validasiNama(namaKaryawan);
                    } while (!validation);
                    
                    
                    do {
                        System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive) : ");
                        jenisKelamin = userInput.next();
                        validasiJk(jenisKelamin);
                    } while (!validation);
                    
                    
                    do {
                        System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive) : ");
                        jabatanKaryawan = userInput.next();    
                        validasiJabatan(jabatanKaryawan);
                    } while (!validation);
                    

                    genGaji(jabatanKaryawan);
                    validasiGaji(gajiKaryawan,jabatanKaryawan);
                    
                    String[] dataKaryawan = new String [5];
                    dataKaryawan[0] = kodeKaryawan;
                    dataKaryawan[1] = namaKaryawan;
                    dataKaryawan[2] = jenisKelamin;
                    dataKaryawan[3] = jabatanKaryawan;
                    dataKaryawan[4] = Integer.toString(gajiKaryawan);

                    System.out.println("===Karyawan Berhasil Ditambah !===");
                    System.out.println("Kode Karyawan : " + dataKaryawan[0]);
                    System.out.println("Nama : " + dataKaryawan[1]);
                    System.out.println("Jenis Kelamin : " + dataKaryawan[2]);
                    System.out.println("Jabatan : " + dataKaryawan[3]);
                    System.out.println("Gaji : " + dataKaryawan[4]);
                    arListKaryawan.add(Arrays.asList(dataKaryawan));
                    
                    Collections.sort(arListKaryawan, new CustomComparator());
                    
                
                break;
        
                case 2:
                    System.out.println("===VIEW KARYAWAN===");
                    viewKaryawan();
                break;

                case 3:
                    System.out.println();
                    System.out.println("===EDIT KARYAWAN===");
                    viewKaryawan();
                    System.out.print("Input nomor urutan karyawan yang ingin diupdate : ");
                    int nomorData = userInput.nextInt();
                    nomorData = nomorData - 1;
                    userInput.nextLine();

                    do {
                        System.out.print("Input nama karyawan [>=3 , Hanya boleh Huruf] : ");
                        namaKaryawan = userInput.next();
                        if (!(namaKaryawan.equals("0"))) {
                            validasiNama(namaKaryawan);
                        }else if (namaKaryawan.equals("0")) {
                            namaKaryawan = arListKaryawan.get(nomorData).get(1);
                            validation = true;
                        }
                    } while (!validation);
                    
                    
                    do {
                        System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive) : ");
                        jenisKelamin = userInput.next();
                        if (!(jenisKelamin.equals("0"))) {
                            validasiJk(jenisKelamin);
                        }else if (jenisKelamin.equals("0")) {
                            jenisKelamin = arListKaryawan.get(nomorData).get(2);
                            validation = true;
                        }
                    } while (!validation);
                
                    do {
                        System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive) : ");
                        jabatanKaryawan = userInput.next();    
                        if (!(jabatanKaryawan.equals("0"))) {   
                            validasiJabatan(jabatanKaryawan);
                        }else if (jabatanKaryawan.equals("0")) {
                            jabatanKaryawan = arListKaryawan.get(nomorData).get(3);
                            validation = true;
                        }
                    } while (!validation);

                    genGaji(jabatanKaryawan);
                  
                    //arListKaryawan.get(i).set(4,Integer.toString(newgaji));
                    arListKaryawan.get(nomorData).set(1, namaKaryawan);
                    arListKaryawan.get(nomorData).set(2, jenisKelamin);
                    arListKaryawan.get(nomorData).set(3, jabatanKaryawan);
                    arListKaryawan.get(nomorData).set(4, Integer.toString(gajiKaryawan));

                    System.out.println("Edit data berhasil untuk Karyawan dengan Kode : " + arListKaryawan.get(nomorData).get(0));
                

                break;

                case 4:
                    System.out.println();
                    System.out.println("===DELETE KARYAWAN===");
                    viewKaryawan();
                    System.out.print("Input nomor urutan karyawan yang ingin didelete : ");
                    nomorData = userInput.nextInt();
                    nomorData = nomorData - 1;
                    userInput.nextLine();
                    System.out.println("Karyawan dengan kode " + arListKaryawan.get(nomorData).get(0) + " Berhasil dihapus" );
                    arListKaryawan.remove(nomorData);

                break;
            
                default :
                    loop = false;
                break;
            }
        } 

        userInput.close();
    }

    public static void viewKaryawan(){
        for(int i = 0; i < arListKaryawan.size(); i++){
            System.out.print("[" + (i+1)  + "]");
            System.out.println(arListKaryawan.get(i));
        }
    }


    public static void validasiNama(String x){
        if (x.matches("^[ A-Za-z]+$") && x.length() >= 3){
            validation = true;
        }
        else{
            validation = false;
        }
    }

    public static void validasiJk(String x){
        if (x.equals("Laki-laki") || x.equals("Perempuan")){
            validation = true;
        }
        else{
            validation = false;
        }
    }

    public static void validasiJabatan(String x){
        if (x.equals("Manager") || x.equals("Supervisor") || x.equals("Admin")){
            validation = true;
        }
        else{
            validation = false;
        }
    }

    public static void validasiKodeKaryawan(String x){
        
        for(int i = 0; i < arListKaryawan.size(); i++){
            String compare = arListKaryawan.get(i).get(0);
            if(compare.equals(kodeKaryawan)){
                System.out.println("Karyawan dengan kode yang sama ditemukan !");
                validation = false;
            }
            else{
                validation = true;
            }   
        }
    }

    public static void validasiGaji(int x,String y){
        int counter = 0;
        for(int i = 0; i < arListKaryawan.size(); i++){
            String compare = arListKaryawan.get(i).get(3);
            if (compare.equals(y)) {
                counter ++;
            }
        }
        
        if (counter % 3 == 0) {
            for(int i = 0; i < arListKaryawan.size(); i++){
                String compare = arListKaryawan.get(i).get(3);
                //Manager
                if (compare.equals(y) && compare.equals("Manager")) {
                    int gaji = Integer.parseInt(arListKaryawan.get(i).get(4));
                    int newgaji = gaji + (gaji*10/100);
                    arListKaryawan.get(i).set(4,Integer.toString(newgaji));
                    System.out.println("Bonus gaji sebesar 10% telah diberikan pada karyawan dengan id : " + arListKaryawan.get(i).get(0));
                }
                //Supervisor
                if (compare.equals(y) && compare.equals("Supervisor")) {
                    int gaji = Integer.parseInt(arListKaryawan.get(i).get(4));
                    int newgaji = gaji + (gaji*75/1000);
                    arListKaryawan.get(i).set(4,Integer.toString(newgaji));
                    System.out.println("Bonus gaji sebesar 7.5% telah diberikan pada karyawan dengan id : " + arListKaryawan.get(i).get(0));
                }
                
                //Admin
                if (compare.equals(y) && compare.equals("Admin")) {
                    int gaji = Integer.parseInt(arListKaryawan.get(i).get(4));
                    int newgaji = gaji + (gaji*5/100);
                    arListKaryawan.get(i).set(4,Integer.toString(newgaji));
                    System.out.println("Bonus gaji sebesar 5% telah diberikan pada karyawan dengan id : " + arListKaryawan.get(i).get(0));
                }
            }
        }
    }

    public static void genGaji(String x){
        switch (x) {
            case "Manager":
                gajiKaryawan = 8000000;
            break;
        
            case "Supervisor" :
                gajiKaryawan = 6000000;
            break;

            case "Admin" :
                gajiKaryawan = 4000000;
            break;
        }
    }

    public static void genKodeKaryawan(){ 
        //2 Huruf
        int leftLimit = 65; // letter 'a'
        int rightLimit = 90; // letter 'z'
        int targetStringLength = 2;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        //4 Angka
        Random random2 = new Random();
        String generatedString2 = String.format("%04d", random2.nextInt(10000));
        kodeKaryawan = generatedString + "-" + generatedString2;
        // System.out.println(kodeKaryawan);
    }
}
