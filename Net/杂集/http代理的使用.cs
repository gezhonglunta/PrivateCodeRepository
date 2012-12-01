using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.IO;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                WebRequest request = HttpWebRequest.Create("http://www.baidu.com");//欲请求的目标服务器，http参数自己拼接
                request.Proxy = new WebProxy("125.88.75.151", 3128);//HTTP代理服务器地址
                request.Method = "GET";//或者POST
                request.Headers.Add("Accept-Encoding", "utf-8");//增加必要的http header,视情况而定
                request.Headers.Add("Accept-Charset", "utf-8");
                WebResponse response = request.GetResponse();
                Stream stream = response.GetResponseStream();
                StreamReader br = new StreamReader(stream, Encoding.UTF8);
                Console.WriteLine(br.ReadToEnd());
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            Console.ReadLine();
        }
    }
}
