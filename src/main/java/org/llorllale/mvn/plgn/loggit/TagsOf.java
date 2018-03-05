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

import java.util.Iterator;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterator.Filtered;
import org.cactoos.scalar.And;
import org.cactoos.scalar.UncheckedScalar;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.xembly.Directive;
import org.xembly.Directives;

/**
 * All tags pointing to a given commit.
 *
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.5.0
 */
final class TagsOf implements Iterable<Directive> {
  private final Repository repo;
  private final RevCommit commit;

  /**
   * Ctor.
   * 
   * @param repo the git repository
   * @param commit the commit for which to fetch the tags
   * @since 0.5.0
   */
  TagsOf(Repository repo, RevCommit commit) {
    this.repo = repo;
    this.commit = commit;
  }

  @Override
  public Iterator<Directive> iterator() {
    return new UncheckedScalar<>(
      () -> {
        final Directives dirs = new Directives();
        final LogCommand log = new org.eclipse.jgit.api.Git(this.repo).log();
        new And(
          tag -> {
            if (new Filtered<>(rev -> rev.equals(this.commit), log.call().iterator()).hasNext()) {
              dirs.add("tag").set(tag.getName().split("/")[2]).up();
            }
            return true;
          },
          new Mapped<>(
            peeled -> {
              log.add(peeled.getPeeledObjectId());
              return peeled;
            },
            new Mapped<>(
              ref -> this.repo.peel(ref),
              new org.eclipse.jgit.api.Git(this.repo).tagList().call()
            )
          )
        ).value();
        return dirs.iterator();
      }
    ).value();
  }
}
