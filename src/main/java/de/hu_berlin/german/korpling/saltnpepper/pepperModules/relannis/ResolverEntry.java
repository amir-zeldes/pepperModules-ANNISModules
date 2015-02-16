/*
 * Copyright 2015 Humboldt Univerity of Berlin, INRIA.
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
package de.hu_berlin.german.korpling.saltnpepper.pepperModules.relannis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Thomas Krause <krauseto@hu-berlin.de>
 */
public class ResolverEntry {

  public enum Element {

    node, edge, NULL
  }

  public enum Visibility {

    hidden, permanent, visible, removed, preloaded
  }

  private String layerName = null;
  private Element element = Element.NULL;
  private String vis = "grid";
  private String display = "visualization";
  private Visibility visibility = Visibility.hidden;
  private int order = 0;

  private final Map<String, String> mappings = new LinkedHashMap<String, String>();

  public String getLayerName() {
    return layerName;
  }

  public void setLayerName(String layerName) {
    this.layerName = layerName;
  }

  public Element getElement() {
    return element;
  }

  public void setElement(Element element) {
    this.element = element;
  }

  public String getVis() {
    return vis;
  }

  public void setVis(String vis) {
    this.vis = vis;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public Visibility getVisibility() {
    return visibility;
  }

  public void setVisibility(Visibility visibility) {
    this.visibility = visibility;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Map<String, String> getMappings() {
    return mappings;
  }

}
