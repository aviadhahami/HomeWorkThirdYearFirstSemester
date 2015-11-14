/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aviadh
 */
public class Main {

    public static void main(String[] args) {
        int port = 9000;
        Server s = new Server();
        s.run(port);
    }
}
