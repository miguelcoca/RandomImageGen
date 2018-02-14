class test{
    public static void main(String[] args){

        RandomImageGen rig = new RandomImageGen(320,240);

        rig.writeImageFile("file","bmp");
        rig.writeImageFile("file","jpg");
        rig.writeImageFile("file","png");
        rig.writeImageFile("file","gif");
    }
}