/**
     * TODO: 生成手机验证码🔓
     * */
    public static String getRandomAuthCode(){
        IdWorker worker = new IdWorker(1,1,1);
        String AuthCodeTmp = String.valueOf(worker.nextId());
        return  AuthCodeTmp.substring(AuthCodeTmp.length() - 6, AuthCodeTmp.length());

    }
    /**
     * TODO: 发送手机验证码📱
     *
     * @return
     */
    public static void SMSVerification(String phoneNumber, String authCode) throws IOException{

        //发送内容
        String content = "您的手机号："+ phoneNumber + "，验证码："+ authCode + "，请及时完成验证，如不是本人操作请忽略。";
        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("https://api.chanyoo.net/sendsms?");

        // 向StringBuffer追加平台帐号
        sb.append("username=Sunshineisbright");

        // 向StringBuffer追加调用密码
        sb.append("&password=20191731");

        // 向StringBuffer追加手机号码
        sb.append("&mobile=" + phoneNumber);

        // 向StringBuffer追加短信内容转URL标准码
        sb.append("&content=").append(URLEncoder.encode(content, "UTF-8"));

        // 创建url对象
        URL url = new URL(sb.toString());
        System.out.println(url.toString());

        // 打开url连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置url请求方式GET或者POST
        connection.setRequestMethod("GET");

        // 发送请求
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

//         返回发送结果
        String inputline = in.readLine();

        // 输出返回结果
        System.out.println(inputline);
    }
