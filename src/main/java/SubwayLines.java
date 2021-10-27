import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SubwayLines {
    List<Train> allTrains = new ArrayList<>();
    List<String> A;
    List<String> B;
    List<String> C;
    List<String> D;
    List<String> E;
    List<String> F;
    List<String> G;
    List<String> H;
    List<String> I;
    List<String> J;
    List<String> K;
    List<String> L;
    List<String> M;
    List<String> N;
    List<String> O;
    List<String> P;
    List<String> Q;
    List<String> R;
    List<String> S;
    List<String> T;
    List<String> U;
    List<String> V;
    List<String> W;
    List<String> X;
    List<String> Y;
    List<String> Z;
    @SerializedName("6 Express")
    List<String> sixExpress;
    @SerializedName("7 Express")
    List<String> sevenExpress;
    @SerializedName("1")
    List<String> one;
    @SerializedName("2")
    List<String> two;
    @SerializedName("3")
    List<String> three;
    @SerializedName("4")
    List<String> four;
    @SerializedName("5")
    List<String> five;
    @SerializedName("6")
    List<String> six;
    @SerializedName("7")
    List<String> seven;

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
        allTrains.add(new Train(S, "S"));
        allTrains.add(new Train(T, "T"));
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
