package beans;

public class DateBean {
    private int id;						//序号
    private String url;					//接口(url)
    private String param;				//传入的参数(可能含变量)
    private String expectedresult;		//预期结果
    private String desc; 				//说明(对接口的说明)
    private boolean run;				//是否执行
    private String inparam;				//请求(实际传入的参数)
    private String outparam;			//返回(实际返回的参数)
    private String variable;			//入参中定义的变量
    private String method;				//执行方式(Get,Post或SQL等)

    private int sleep;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getExpectedresult() {
        return expectedresult;
    }

    public void setExpectedresult(String expectedresult) {
        this.expectedresult = expectedresult;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public String getInparam() {
        return inparam;
    }

    public void setInparam(String inparam) {
        this.inparam = inparam;
    }

    public String getOutparam() {
        return outparam;
    }

    public void setOutparam(String outparam) {
        this.outparam = outparam;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("desc:%s,method:%s,url:%s,param:%s", this.desc,
                this.method, this.url, this.param);
    }
}
