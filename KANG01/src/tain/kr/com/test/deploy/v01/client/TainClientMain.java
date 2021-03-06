/**
 * Copyright 2014, 2015, 2016 TAIN, Inc. all rights reserved.
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -----------------------------------------------------------------
 * Copyright 2014, 2015, 2016 TAIN, Inc.
 *
 */
package tain.kr.com.test.deploy.v01.client;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import tain.kr.com.test.deploy.v01.client.tr.TR0000;
import tain.kr.com.test.deploy.v01.client.tr.TR0100;
import tain.kr.com.test.deploy.v01.client.tr.TR0200;
import tain.kr.com.test.deploy.v01.client.tr.TR0500;
import tain.kr.com.test.deploy.v01.common.ParamMap;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TainClientMain.java
 *   -. Package    : tain.kr.com.test.deploy.v01.client
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TainClientMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainClientMain.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static String className = null;
	private static ResourceBundle resourceBundle = null;
	
	private static int seqStart = -1;
	private static int seqFinish = -1;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void init() throws Exception {
		
		if (flag) {
			className = new Object(){}.getClass().getEnclosingClass().getName();
			
			resourceBundle = ResourceBundle.getBundle(className.replace('.', '/'));
		}
		
		if (flag) {
			/*
			 * sequence range
			 */
			String strSeqRange = null;
			if (!flag)
				strSeqRange = System.getProperty("tain.job.seq.range", "1-10");
			else
				strSeqRange = System.getProperty("tain.job.seq.range");
			
			String[] arrSeq = strSeqRange.split("-");
			
			seqStart = Integer.parseInt(arrSeq[0]);
			seqFinish = Integer.parseInt(arrSeq[1]);
		}
		
		if (flag) ParamMap.getInstance().printList();
		
		if (flag) {
			log.debug(">>>>> " + className);
			log.debug(">>>>> seqStart=" + seqStart + ", seqFinish=" + seqFinish);
			System.exit(0);
		}
	}
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			
			String key = null;
			String val = null;
			Thread thr = null;
			
			for (int seq=seqStart; seq <= seqFinish; seq++) {
				
				key = "tain.job.seq." + String.format("%d", seq);
				
				try {
					val = resourceBundle.getString(key);
				} catch (MissingResourceException e) {
					val = null;
				}
				
				if (!flag) log.debug("[" + key + ":" + val + "]");
				
				if (val == null)
					continue;
				
				if (flag) log.debug("########## START ########## [trCode:" + val + "]");

				switch (val) {
				case "TR0000":
					thr = new TR0000();
					thr.start();
					thr.join();
					break;
				case "TR0100":
					thr = new TR0100();
					thr.start();
					thr.join();
					break;
				case "TR0200":
					thr = new TR0200();
					thr.start();
					thr.join();
					break;
				case "TR0500":
					thr = new TR0500();
					thr.start();
					thr.join();
					break;
				default:
					if (flag) log.error(String.format("tr code error [%s]", val));
					break;
				}
				
				if (flag) log.debug("########## FINISH ########## [trCode:" + val + "]\n\n");
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

		if (flag) init();
		
		if (flag) test01(args);
	}
}
