public class test {
    public static void main(String[] args) {
        
    towerOfHanoi(3, 'a','b','c');

    


}
public static void  towerOfHanoi(int n, char fromRod, char  toRod,  char auxRod){
        if (n == 0){
            return;
        }
        towerOfHanoi(n - 1, fromRod, auxRod, toRod);
        System.out.println("Disk " + n + " moved from " + fromRod +
        " to " + toRod);
        towerOfHanoi(n - 1, auxRod, toRod, fromRod);
    }

}