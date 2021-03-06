/*
 * Copyright 2018 George Aristy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.llorllale.mvn.plgn.loggit;

import com.jcabi.xml.XML;
import java.io.IOException;

/**
 * A {@link Git} repo's log.
 *
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.1.0
 */
public interface Log {
  /**
   * Commits from this log.
   * 
   * @return commits from this log
   * @throws IOException if error reading git repo
   * @since 0.1.0
   */
  Iterable<Commit> commits() throws IOException;

  /**
   * This {@link Log} as XML.
   * 
   * @return an XML view of this {@link Log}
   * @throws IOException if error reading git repo
   * @since 0.1.0
   */
  XML asXml() throws IOException;
}
