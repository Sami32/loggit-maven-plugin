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

package org.llorllale.mvn.plgn.gitlog;

import org.junit.Test;

/**
 * Tests for {@link DefaultCommit}.
 *
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.1.0
 */
public class DefaultCommitTest {
  @Test(expected = UnsupportedOperationException.class)
  public void asXmlUnsupported() {
    new DefaultCommit(null).asXml();
  }
}