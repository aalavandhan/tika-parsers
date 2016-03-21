/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @Author: Arturo Beltran
 */
package org.apache.tika.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.rosaloves.bitlyj.Url;

import static com.rosaloves.bitlyj.Bitly.*;

public class UidParser implements Parser {

	private static final long serialVersionUID = 7696361187979692927L;

	public Set<MediaType> getSupportedTypes(ParseContext context) {
    	return Collections.emptySet();
    }
	
	private String baseUrl = "http://sng.usc.edu/polar";

    public void parse(
                    InputStream stream, ContentHandler handler,
                    Metadata metadata, ParseContext context)
                    throws IOException, SAXException, TikaException {
    	
    	String bitlyUserName = "o_532gs7ne8t";
    	String bitlyKey = "R_4c9cb3879c11422bb83ccb9588c054f7";
    	
    	try{
    		String filePath = metadata.getValues("source-file-path")[0];
    		String uid = as(bitlyUserName, bitlyKey).call(shorten(baseUrl + filePath)).getShortUrl();
        	metadata.set("uid", uid);
    	} catch(ArrayIndexOutOfBoundsException e){
    		throw new IOException("source-file-path not set");
    	}
    }
}