/*
 * Copyright 2013 juan.calderon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cimav.server;

import cimav.client.GWTService;
import cimav.shared.Usuario;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

/**
 *
 * @author juan.calderon
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    private static final Logger log = Logger.getLogger(GWTServiceImpl.class.getCanonicalName());

    public interface JsonCodec extends JsonEncoderDecoder<Usuario> {}
    public JsonEncoderDecoder jsonCodec = GWT.create(JsonCodec.class);
    
    @Override
    public Usuario loginProfile(String tokenAutorizado) {

        String url = "https://www.googleapis.com/oauth2/v2/userinfo?alt=json&access_token=" + tokenAutorizado;

        final StringBuffer reader = new StringBuffer();
        try {
            final URL u = new URL(url);
            final URLConnection uc = u.openConnection();
            final int end = 1000;
            InputStreamReader isr;
            BufferedReader br = null;
            try {
                isr = new InputStreamReader(uc.getInputStream());
                br = new BufferedReader(isr);
                final int chk = 0;
                while ((url = br.readLine()) != null) {
                    if ((chk >= 0) && ((chk < end))) {
                        reader.append(url).append('\n');
                    }
                }
            } catch (final java.net.ConnectException cex) {
                reader.append(cex.getMessage());
            } catch (final Exception ex) {
                log.log(Level.SEVERE, ex.getMessage());
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (final Exception ex) {
                    log.log(Level.SEVERE, ex.getMessage());
                }
            }
        } catch (final Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }

        String json = reader.toString();
        JSONValue val = JSONParser.parseStrict(json);
        Usuario usuario = (Usuario) jsonCodec.decode(val);

        return usuario;
    }

}
