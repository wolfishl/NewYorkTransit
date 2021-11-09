import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SubwayLines {
    List<Train> allTrains = new ArrayList<>();
    List<Integer> A;
    List<Integer> B;
    List<Integer> C;
    List<Integer> D;
    List<Integer> E;
    List<Integer> F;
    List<Integer> G;
    List<Integer> H;
    List<Integer> I;
    List<Integer> J;
    List<Integer> K;
    List<Integer> L;
    List<Integer> M;
    List<Integer> N;
    List<Integer> O;
    List<Integer> P;
    List<Integer> Q;
    List<Integer> R;
//    List<Integer> S;
//    List<Integer> T;
    List<Integer> U;
    List<Integer> V;
    List<Integer> W;
    List<Integer> X;
    List<Integer> Y;
    List<Integer> Z;
    @SerializedName("6 Express")
    List<Integer> sixExpress;
    @SerializedName("7 Express")
    List<Integer> sevenExpress;
    @SerializedName("1")
    List<Integer> one;
    @SerializedName("2")
    List<Integer> two;
    @SerializedName("3")
    List<Integer> three;
    @SerializedName("4")
    List<Integer> four;
    @SerializedName("5")
    List<Integer> five;
    @SerializedName("6")
    List<Integer> six;
    @SerializedName("7")
    List<Integer> seven;

    public void addToList()
    {
        allTrains.add(new Train(A, "A"));
        allTrains.add(new Train(B, "B"));
        allTrains.add(new Train(C, "C"));
        allTrains.add(new Train(D, "D"));
        allTrains.add(new Train(E, "E"));
        allTrains.add(new Train(F, "F"));
        allTrains.add(new Train(G, "G"));
        allTrains.add(new Train(H, "H"));
        allTrains.add(new Train(I, "I"));
        allTrains.add(new Train(J, "J"));
        allTrains.add(new Train(K, "K"));
        allTrains.add(new Train(L, "L"));
        allTrains.add(new Train(M, "M"));
        allTrains.add(new Train(N, "N"));
        allTrains.add(new Train(O, "O"));
        allTrains.add(new Train(P, "P"));
        allTrains.add(new Train(Q, "Q"));
        allTrains.add(new Train(R, "R"));
//        allTrains.add(new Train(S, "S"));
//        allTrains.add(new Train(T, "T"));
        allTrains.add(new Train(U, "U"));
        allTrains.add(new Train(V, "V"));
        allTrains.add(new Train(W, "W"));
        allTrains.add(new Train(X, "X"));
        allTrains.add(new Train(Y, "Y"));
        allTrains.add(new Train(Z, "Z"));
        allTrains.add(new Train(sixExpress, "6 Express"));
        allTrains.add(new Train(sevenExpress, "7 Express"));
        allTrains.add(new Train(one, "1"));
        allTrains.add(new Train(two, "2"));
        allTrains.add(new Train(three, "3"));
        allTrains.add(new Train(four, "4"));
        allTrains.add(new Train(five, "5"));
        allTrains.add(new Train(six, "6"));
        allTrains.add(new Train(seven, "7"));
    }




}
