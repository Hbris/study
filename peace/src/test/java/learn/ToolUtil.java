package learn;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class ToolUtil
{

	private static Logger logger = LoggerFactory.getLogger(ToolUtil.class);

	/**
	 * 接口验证
	 * 
	 * @param sourceKey
	 * @param time
	 * @return
	 */



	/**
	 * openapi接受请求时参数校验
	 * @param data
	 * @return
	 */
	public static synchronized String encodeMd5(String data)
	{
		MessageDigest digest = null;
		if (digest == null)
		{
			try
			{
				digest = MessageDigest.getInstance("MD5");
				digest.update(data.getBytes("UTF-8"));
				byte[] hash = digest.digest();

				StringBuffer buf = new StringBuffer(hash.length * 2);
				int i;
				for (i = 0; i < hash.length; i++)
				{
					if ((hash[i] & 0xff) < 0x10)
					{
						buf.append("0");
					}
					buf.append(Long.toString(hash[i] & 0xff, 16));
				}
				return buf.toString();
			}
			catch (Exception nsae)
			{
				System.err.println("Failed to load the MD5 MessageDigest. ");
			}
		}
		return null;
	}
}
